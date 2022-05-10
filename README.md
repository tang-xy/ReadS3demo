# ReadS3demo

A demo about random reading of (primarily non-public) files available in AWS S3 buckets on JVM.

## Dependencies

This demo is dependent on `org.gdal:gdal = 2.2` and `com.amazonaws:aws-java-sdk >= 1.11.538`.

It requires GDAL to be built against libcurl.

To build GDAL, you can build from source like:
```shell
./configure --with-curl=/usr/bin/curl-config --with-java --with-proj=/usr/lib/x86_64-linux-gnu/libproj.la

make
cd swig/java
make
```

## About

Starting with GDAL 3.2, direct writing of GeoTIFF files with the GTiff driver is supported.

The `CPL_VSIL_USE_TEMP_FILE_FOR_RANDOM_WRITE` configuration option is set to YES, in which case random-write access is possible (involves the creation of a temporary local file, whose location is controlled by the `CPL_TMPDIR` configuration option).

Recognized filenames are of the form `/vsis3/bucket/key`, where bucket is the name of the S3 bucket and key is the S3 object “key”, i.e. a filename potentially containing subdirectories.
