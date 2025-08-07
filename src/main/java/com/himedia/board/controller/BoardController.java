package com.himedia.board.controller;

import com.himedia.board.dto.BoardDto;
import com.himedia.board.dto.Paging;
import com.himedia.board.service.BoardService;
import com.himedia.board.service.S3UploadService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

@Controller
public class BoardController {
    @Autowired
    BoardService bs;
    @Autowired
    ServletContext context;

    @GetMapping("/main")
    public ModelAndView mainPage(HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        result = bs.selectBoard(request);
        ArrayList<BoardDto> list = (ArrayList<BoardDto>) result.get("boardList");
        Paging paging = (Paging)result.get("paging");
        ModelAndView mav = new ModelAndView();
        mav.addObject("boardList", list);
        mav.addObject("paging", paging);
        // mav.addObject("boardList", result.get("boardList"));
        // mav.addObject("paging", result.get("paging"));

        mav.setViewName("main");
        return mav;
    }

    @GetMapping("/boardView")
    public ModelAndView boardView(@RequestParam("num") int num) {
        ModelAndView mav = new ModelAndView();
        HashMap<String, Object> result = bs.getBoard(num);
        mav.addObject("board", result.get("board"));
        mav.addObject("replyList", result.get("replyList"));
        mav.setViewName("board/boardView");
        return mav;
    }

    @GetMapping("/insertBoardForm")
    public String insertBoardForm() {
        return "board/insertBoard";
    }
    /*
    @PostMapping("/insertBoard")
    public String insertBoard(@Valid BoardDto boarddto, BindingResult result,
                              @RequestParam("uploadImage") MultipartFile file, Model model) {
        System.out.println("boarddto : " + boarddto);
        String url = "board/insertBoard";
        if(result.hasFieldErrors("title"))
            model.addAttribute("msg", result.getFieldError("title").getDefaultMessage());
        else if(result.hasFieldErrors("pass"))
            model.addAttribute("msg", result.getFieldError("pass").getDefaultMessage());
        else if(result.hasFieldErrors("content"))
            model.addAttribute("msg", result.getFieldError("content").getDefaultMessage());
        else {
            url = "redirect:/main";
            // 파일 업로드
            String path = context.getRealPath("/images");
            String filename = file.getOriginalFilename();
            Calendar today = Calendar.getInstance();
            long t = today.getTimeInMillis();
            String fn1 = filename.substring(0, filename.indexOf(".")); // abc.jsp -> abc
            String fn2 = filename.substring(filename.indexOf(".")); // abc.jsp -> .jsp
            String uploadFilePath = path + "/" + fn1 + t + fn2;
            String saveFilename = fn1 + t + fn2;
            try {
                file.transferTo(new File(uploadFilePath)); // 파일 업로드
            }catch(IllegalStateException | IOException e) {
                e.printStackTrace();
            }

            boarddto.setImage(filename);
            boarddto.setSavefilename(saveFilename);
            // 게시글 등록
            bs.insert(boarddto);
        }
        model.addAttribute("dto", boarddto);
        return url;
    }
     */
    @GetMapping("/selectimg")
    public String selectimg() {
        return "board/selectimg";
    }

    @Autowired
    S3UploadService sus;

    @PostMapping("/fileupload")
    public String fileupload(@RequestParam("image") MultipartFile file,
                             HttpServletRequest request, Model model) {
        try {
            // 파일 업로드 후 경로와 파일명을 리턴
            String uploadFilePathName = sus.saveFile(file);
            String filename = file.getOriginalFilename();
            model.addAttribute("image", filename);
            model.addAttribute("savefilename", uploadFilePathName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "board/completeUpload";
    }
    /*
    @PostMapping("/fileupload")
    public String fileupload(@RequestParam("image") MultipartFile file,
                                   HttpServletRequest request, Model model) {
        String path = context.getRealPath("/images");
        String filename = file.getOriginalFilename();
        Calendar today = Calendar.getInstance();
        long t = today.getTimeInMillis();
        String fn1 = filename.substring(0, filename.indexOf(".")); // abc.jsp -> abc
        String fn2 = filename.substring(filename.indexOf(".")); // abc.jsp -> .jsp
        String uploadFilePath = path + "/" + fn1 + t + fn2;
        String saveFilename = fn1 + t + fn2; // 저장 파일 이름 생성
        String uploadPath = path + "/" + saveFilename; // 저장 파일 경로 생성
        try {
            file.transferTo(new File(uploadPath)); // 파일 업로드
        }catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("image", filename); // 사용자가 선택한 이름
        model.addAttribute("savefilename", saveFilename); // 서버에 저장되는 이름
        return "board/completeUpload";
    }
     */

    @PostMapping("/insertBoard")
    public String insertBoard(@ModelAttribute("dto") @Valid BoardDto boarddto, BindingResult result, Model model) {
        String url = "board/insertBoard";
        if(result.hasFieldErrors("title"))
            model.addAttribute("msg", result.getFieldError("title").getDefaultMessage());
        else if(result.hasFieldErrors("pass"))
            model.addAttribute("msg", result.getFieldError("pass").getDefaultMessage());
        else if(result.hasFieldErrors("content"))
            model.addAttribute("msg", result.getFieldError("content").getDefaultMessage());
        else {
            url = "redirect:/main";
            bs.insert(boarddto);
        }
        return url;
    }

    @GetMapping("/updateBoardForm")
    public ModelAndView updateBoardForm(@RequestParam("num") int num) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("dto", bs.getBoardOne(num));
        mav.addObject("oldfilename", bs.getBoardOne(num).getSavefilename());
        mav.setViewName("board/updateBoard");
        return mav;
    }

    @PostMapping("/updateBoard")
    public String updateBoard(@ModelAttribute("dto") @Valid BoardDto boarddto, BindingResult result,
                              @RequestParam("oldfilename") String oldfilename, Model model) {
        model.addAttribute("oldfilename", oldfilename);
        String url = "board/updateBoard";
        BoardDto bdto = bs.getBoardOne(boarddto.getNum());
        if(result.hasFieldErrors("pass"))
            model.addAttribute("msg", result.getFieldError("pass").getDefaultMessage());
        else if(result.hasFieldErrors("title"))
            model.addAttribute("msg", result.getFieldError("title").getDefaultMessage());
        else if(result.hasFieldErrors("content"))
            model.addAttribute("msg", result.getFieldError("content").getDefaultMessage());
        else if(!boarddto.getPass().equals(bdto.getPass()))
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
        else {
            url = "redirect:/boardViewWithoutCnt?num=" + bdto.getNum();
            bs.update(boarddto);
        }
        return url;
    }

    @GetMapping("/boardViewWithoutCnt")
    public ModelAndView BoardViewWithoutCnt(@RequestParam("num") int num) {
        ModelAndView mav = new ModelAndView();
        HashMap<String, Object> result = bs.getBoardWithoutCnt(num);
        mav.addObject("board", result.get("board"));
        mav.addObject("replyList", result.get("replyList"));
        mav.setViewName("board/boardView");
        return mav;
    }

    @GetMapping("/deleteBoard")
    public String deleteBoard(@RequestParam("num") int num, @RequestParam("pass") String pass, Model model) {
        BoardDto bdto = bs.getBoardOne(num);
        if(bdto.getPass().equals(pass)) {
            bs.delete(num);
            return "redirect:/main";
        }else {
            model.addAttribute("num", num);
            return "board/deleteFail";
        }
    }

    @GetMapping("/updatePwdForm")
    public String updatePwdForm(@RequestParam("num") int num, Model model) {
        model.addAttribute("num", num);
        return "board/updatePwd";
    }

    @PostMapping("/updatePwd")
    public String updatePwd(
            @RequestParam(value="oldPwd", required=false) String oldPwd,
            @RequestParam(value="newPwd", required=false) String newPwd,
            @RequestParam(value="confirmPwd", required=false) String confirmPwd,
            @RequestParam("num") int num, Model model
    ) {
        String url = "board/updatePwd";
        BoardDto boarddto = bs.getBoardOne(num);
        model.addAttribute("num", num);
        if(oldPwd.equals("") || newPwd.equals("") || confirmPwd.equals("")) {
            model.addAttribute("msg", "입력창을 전부 채워주세요.");
        }else if(!boarddto.getPass().equals(oldPwd)) {
            model.addAttribute("msg", "기존 비밀번호가 일치하지 않습니다.");
        }else if(!newPwd.equals(confirmPwd)) {
            model.addAttribute("msg", "새 비밀번호 확인이 일치하지 않습니다.");
        }else {
            bs.updatePwd(num, newPwd);
            url = "board/completeUpdatePwd";
        }
        return url;
    }
}
