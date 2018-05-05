---
title: spring boot 文件上传
---

> demo测试通过，使用版本如下：
> spring boot版本：1.5.10.RELEASE
> java版本：1.8
> maven版本：apache-maven-3.3.9

**spring boot文件上传参考示例**

- #### 单文件上传

````
    /**
     * 单文件上传
     * @param file
     * @return
     */
    @PostMapping(value = "/uploadfile")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            try {
                //指定路径加文件名就会将文件保存到指定的路径下，
                String file_upload_path = "D:\\uploadfile\\"+file.getOriginalFilename();
                //没有指定就保存到项目所有的目录下
                //String file_upload_path = file.getOriginalFilename();
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(file_upload_path)));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            return "上传成功";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }
````

<!-- more -->

- #### 多文件上传

```
/**
     * 多文件上传 主要是使用了MultipartHttpServletRequest和MultipartFile
     * @param request
     * @return
     */
    @PostMapping(value = "/upload/batch")
    public  String batchUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                //指定路径加文件名就会将文件保存到指定的路径下，
                String file_upload_path = "D:\\uploadfile\\"+file.getOriginalFilename();
                //没有指定就保存到项目所有的目录下
                //String file_upload_path = file.getOriginalFilename();
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(file_upload_path)));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "上传失败 " + i + " => " + e.getMessage();
                }
            } else {
                return "上传失败，文件为空";
            }
        }
        return "上传成功";
    }
```

- #### 配置信息

````
#设置上传文件的最大限制
spring.http.multipart.max-file-size=10MB
#设置总上传的数据大小
spring.http.multipart.max-request-size=100MB
#设置文件上传的临时路径
spring.http.multipart.location=临时路径地址
````