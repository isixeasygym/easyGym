package com.isix.easyGym.freeboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.isix.easyGym.freeboard.dto.FreeBoardDTO;
import com.isix.easyGym.freeboard.dto.fbImageDTO;
import com.isix.easyGym.freeboard.service.FreeBoardServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("FreeBoardControllerImpl")
public class FreeBoardControllerImpl implements FreeBoardController {
    private static final String ARTICLE_IMG_REPO = "C:\\temp\\article_images";

    @Autowired
    private FreeBoardServiceImpl boardService;

    @Autowired
    private FreeBoardDTO freeBoardDTO;

    @GetMapping("/views/listArticles")
    @Override
    public ModelAndView listFBoard(String section, String pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<FreeBoardDTO> articles = boardService.listArticles();
        ModelAndView mav = new ModelAndView("listArticles");
        mav.addObject("articles", articles);
        return mav;
    }

    @GetMapping("/board/writeArticle")
    @Override
    public ModelAndView fBoardForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("writeArticle");
    }

    @PostMapping("/board/write")
    @Override
    public ModelAndView addFBoard(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        Enumeration<String> enu = request.getParameterNames();
        FreeBoardDTO article = new FreeBoardDTO();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            String value = request.getParameter(name);
            if (name.equals("title")) {
                article.setTitle(value);
            } else if (name.equals("content")) {
                article.setContent(value);
            }
        }

        HttpSession session = request.getSession();
        article.setId((String) session.getAttribute("id"));

        List<fbImageDTO> imageFileList = new ArrayList<>();
        List<MultipartFile> fileList = request.getFiles("file");
        for (MultipartFile mFile : fileList) {
            String originalFileName = mFile.getOriginalFilename();
            File file = new File(ARTICLE_IMG_REPO + "\\temp\\" + originalFileName);
            file.getParentFile().mkdirs();
            mFile.transferTo(file);
            fbImageDTO image = new fbImageDTO();
            image.setImageFileName(originalFileName);
            imageFileList.add(image);
        }
        article.setImageFileList(imageFileList);
        boardService.addArticle(article);

        return new ModelAndView("redirect:/board/list");
    }

    @GetMapping("/board/view")
    @Override
    public ModelAndView viewFBoard(@RequestParam("articleNo") int articleNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FreeBoardDTO article = boardService.viewArticle(articleNo);
        ModelAndView mav = new ModelAndView("viewArticle");
        mav.addObject("article", article);
        return mav;
    }

    @Override
    public ModelAndView modFBoard(MultipartHttpServletRequest MultipartRequest, HttpServletResponse response) throws Exception {
        // Implement modify board logic
        return null;
    }

    @Override
    public ModelAndView removeFBoard(@RequestParam("articleNo") int articleNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Implement remove board logic
        return null;
    }
}
