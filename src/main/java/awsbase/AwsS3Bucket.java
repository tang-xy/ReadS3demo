package awsbase;

import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AwsS3Bucket extends AwsS3Client {


        private String bucketName = "testbucket";
        /**
         * 列出所有的s3桶
         */
        public void listBuckets() {
            List<Bucket> buckets = s3.listBuckets();
            buckets.forEach(item -> {
                System.out.println(item.toString());
            });
        }

        /**
         * 创建一个s3桶-默认
         */
        public void createBucketBase() {
            Bucket bucket = s3.createBucket(bucketName);
            System.out.println(bucket.toString());
        }

        /**
         * 创建一个s3桶-带参数
         */
        public void createBucketWithParams(String bucketname) {
            bucketName = bucketname;
            //指定名称和区域
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);

            //创建公开可读的桶
            //ACL有如下几种"private","public-read","public-read-write","authenticated-read"
            createBucketRequest.setCannedAcl(CannedAccessControlList.PublicReadWrite);
            //是否启用对象锁-启用后，阻止删除对象
            createBucketRequest.setObjectLockEnabledForBucket(false);
            Bucket bucket = s3.createBucket(createBucketRequest);
            System.out.println(bucket.toString());
        }

        /**
         * 删除一个s3桶
         */
        public void deleteBucket() {
            DeleteBucketRequest deleteBucketRequest = new DeleteBucketRequest(bucketName);
            s3.deleteBucket(deleteBucketRequest);
            System.out.println("delete bucket success");
        }
        /**
        * 上传对象文件
        */
        public  void uploadFile(String key, String filePath) {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, new File(filePath));
            PutObjectResult putObjectResult = s3.putObject(putObjectRequest);
            System.out.println(putObjectResult.getETag());
        }
        /**
        * 获取文件流
        */
        public StringBuffer downloadFile(String key) throws IOException {
            S3Object s3Object = s3.getObject(bucketName, key);

            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
            StringBuffer stringBuffer = new StringBuffer();
            byte[] buffer = new byte[1024];
            while ((s3ObjectInputStream.read(buffer)) != -1) {
                stringBuffer.append(new String(buffer, "UTF-8"));
            }
            s3ObjectInputStream.close();
            return stringBuffer;
        }
}
