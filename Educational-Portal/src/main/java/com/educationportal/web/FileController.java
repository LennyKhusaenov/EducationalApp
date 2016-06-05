package com.educationportal.web;

import com.educationportal.StartApplication;

import java.io.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Администратор on 29.05.2016.
 */
@Controller
@RequestMapping(value = "/upload")
public class FileController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String provideUploadInfo(Model model) {
        File rootFolder = new File(StartApplication.ROOT);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("files",
                Arrays.stream(rootFolder.listFiles())
                        .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
                        .collect(Collectors.toList())

        );

        return "uploadFile";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/file")
    public String provideUploadFile(@RequestParam("name") String itemName,HttpServletResponse response) throws IOException {

        File rootFolder = new File(StartApplication.ROOT);
        List<File> files = Arrays.stream(rootFolder.listFiles())
                .collect(Collectors.toList());
        File foundFile = null;
        for (File file:files) {
            if (itemName.equals(file.getName())){
                foundFile = file;
                break;
            }
        }
        if (foundFile == null){
            return "404";
        }

        FileInputStream inputStream = new FileInputStream(foundFile);  //read the file
        response.setHeader("Content-Disposition","attachment; filename=".concat(foundFile.getName()));
        try {
            int c;
            while ((c = inputStream.read()) != -1) {
                response.getWriter().write(c);
            }
        } finally {
            if (inputStream != null)
                inputStream.close();
            response.getWriter().close();
        }
        response.flushBuffer();
        return "";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public String handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
            return "redirect:/";
        }
        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
            return "redirect:/";
        }

        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(StartApplication.ROOT + "/" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + name + "!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to upload " + name + " because the file was empty");
        }

        return "redirect:/";
    }

}
