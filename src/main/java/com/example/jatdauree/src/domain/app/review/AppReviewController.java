package com.example.jatdauree.src.domain.app.review;


import com.example.jatdauree.config.BaseException;
import com.example.jatdauree.config.BaseResponse;
import com.example.jatdauree.src.domain.app.review.dto.*;
import com.example.jatdauree.src.domain.app.review.service.AppReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/jat/app/reivews")
public class AppReviewController {
    private final AppReviewService appReviewService;

    @Autowired
    public AppReviewController(AppReviewService appReviewService){
        this.appReviewService = appReviewService;
    }

    /**
     * Controller -1
     * 23.07.19 작성자 : 윤다은
     * 구매자가 리뷰 작성
     * POST /jat/app/reivews 리뷰 작성하기
     *
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostReviewRes> postReview(PostReviewReq postReviewReq){
        try{
            PostReviewRes postReviewRes = appReviewService.postReview(postReviewReq);
            return new BaseResponse<> (postReviewRes);
        }catch (BaseException baseResponse){
            return new BaseResponse<>(baseResponse.getStatus());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Controller -2
     * 23.07.20 작성자 : 윤다은
     * 마이 떨이에서 모든 리뷰 조회
     * POST /jat/app/reivews 모든 리뷰 조회하기
     *
     */
    @ResponseBody
    @GetMapping("/{customerIdx}")
    public BaseResponse<GetReviewRes> myReviews(@PathVariable int customerIdx){
        try{
            GetReviewRes getReviewRes = appReviewService.myReviews(customerIdx);
            return new BaseResponse<>(getReviewRes);
        }catch (BaseException baseResponse){
            return new BaseResponse<>(baseResponse.getStatus());
        }
    }

    /**
     * Controller -3
     * 23.07.20 작성자 : 윤다은
     * 리뷰 삭제하기
     * PATCH /jat/app/reivews
     */
    @ResponseBody
    @PatchMapping("")
    public BaseResponse<PatchReviewRes> deleteReview(@RequestBody PatchReviewReq patchReviewReq){
        try{
            PatchReviewRes patchReviewRes = appReviewService.deleteReview(patchReviewReq);
            return new BaseResponse<>(patchReviewRes);
        }catch (BaseException baseException){
            return new BaseResponse<>(baseException.getStatus());
        }
    }
    /**
     * Controller -4
     * 23.07.20 작성자 : 윤다은
     * 리뷰 신고하기
     * POST /jat/app/reivews/report
     */
    @ResponseBody
    @PostMapping("/report")
    public BaseResponse<ReportReviewRes> reportReview(@RequestBody ReportReviewReq reportReviewReq){
        try{
            ReportReviewRes reportReviewRes = appReviewService.reportReview(reportReviewReq);
            return new BaseResponse<>(reportReviewRes);
        }catch (BaseException baseException){
            return new BaseResponse<>(baseException.getStatus());
        }
    }



}
