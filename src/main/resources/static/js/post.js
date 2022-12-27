/**
 *  회원가입시 Ajax를 사용하는 이유
 *
 *  1. 요청에 대한 응답을 html이 아닌 Data(Json)로 받기 위해서이다.
 *  >> form 요청은 html로 응답을 받는데 이건 구형 방식임
 *  >> 서버에서 웹은 html로 받고 앱은 json으로 데이터를 받는데, 웹과 앱을 통합하기 위해 json을 보내는 방식으로 서버를 통합함
 *
 *  2. 비동기 통신을 하기 위해서이다.
 *  >> 일반 통신이 일어나면 앱이 잠깐 멈추는데, 비동기 통신이 이걸 막아준다.
 *
 */
//https://chaelin1211.github.io/study/2021/04/14/thymeleaf-ajax.html
let index = {
//    init: function () {
//        $("#btn-comment-save").on("click", () => {
//            this.commentSave();
//        });
//    },

    commentSave: function () {
        let data = {
            "postId" : $("#postId").val(),
            "memberId" : $("#memberId").val(),
            "content": $("#comment-content").val()
        };

        // 데이터에 담을게 아니니 따로 뺌, int형이니 .val() 붙임
        console.log(JSON.stringify(data));

        $.ajax({
            type: "POST",
            url: `/post/${data.postId}/comment`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
//            dataType: "json" //서버에서 받을 데이터 타입 정의
        }).done(function () {
            alert("댓글 작성이 완료되었습니다.");
//            $('comment-box').replaceWith(fragment);
            location.href = `/post/${data.postId}/detail`;
        }).fail(function (error) {
            alert("댓글 작성 실패");
            alert(JSON.stringify(error));
        })
    },

    commentDelete: function (postId, commentId) {
        $.ajax({
            // 댓글 삭제
            type: "DELETE",
            url: `/post/${postId}/comment/${commentId}`,
        }).done(function (res) {
            alert("댓글 삭제 성공");
            location.href = `/post/${postId}/detail`;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
}


//index.init();