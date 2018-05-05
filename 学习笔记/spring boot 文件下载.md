---
title: spring boot 文件下载
---

> demo测试通过，使用版本如下：
> spring boot版本：1.5.10.RELEASE
> java版本：1.8
> maven版本：apache-maven-3.3.9

**spring boot 文件下载参考示例**

```
    /**
     * 测试文件下载示例
     * @return
     * @throws Exception
     */
    @GetMapping("/download")
    public ResponseEntity download() throws Exception{
        //要下载的文件（路径+文件名）
        String file_path = "D:\\uploadfile\\test.xlsx";
        //要下载的文件资源
        FileSystemResource fileSystemResource = new FileSystemResource(file_path);

        HttpHeaders httpHeaders = new HttpHeaders();
        //下载的文件名称
        String down_load_file_name = "test.xlsx";
        //设置下载文件的默认名称
        httpHeaders.add("Content-Disposition","attachment;filename="+down_load_file_name);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentLength(fileSystemResource.contentLength())
                //application/x-download或者application/octet-stream都可以下载
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(fileSystemResource.getInputStream()));
    }
```