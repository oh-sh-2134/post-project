function objectifyForm(formArray) {//serializeArray data function
		var returnArray = {};
		for (var i = 0; i < formArray.length; i++) {
			returnArray[formArray[i]['name']] = formArray[i]['value'];
		}
		return returnArray;
	}

function usernameCheck() {
    var memberCreateDto = (objectifyForm($("#memberCreateDto").serializeArray()))
//    alert(memberCreateDto);
//    console.log(memberCreateDto);
    $.ajax({
        //get으로 보내면 json이 아닌 parm 형식으로 나감
        type: "post",
        url: "/userIdDuplicationCheck",
        data: JSON.stringify(memberCreateDto),
        contentType : "application/json; charset=utf-8",
        dataType: "json",

        success: function (result) {
            if (result == 0) {
                if (confirm("이 아이디는 사용 가능합니다. \n사용하시겠습니까?")) {
                    usernameOverlapCheck = 1;
                    $("#username").attr("readonly", true);
                    $("#usernameOverlay").attr("disabled", true);
                    $("#usernameOverlay").css("display", "none");
                    $("#resetUsername").attr("disabled", false);
                    $("#resetUsername").css("display", "inline-block");
                }
                return false;
            } else if (result == 1) {
                alert("이미 사용중인 아이디입니다.");
//                memberCreateDto.focus();
            } else {
                alert("success이지만 result 값이 undefined 잘못됨");
            }
        },
        error: function (request, status,error) {
            alert("ajax 실행 실패");
            alert("code:" + request.status + "\n" + "error :" + error);
        }
    });

}

function NicknameCheck() {
    var memberCreateDto = (objectifyForm($("#memberCreateDto").serializeArray()))
//    alert(memberCreateDto);
//    console.log(memberCreateDto);
    $.ajax({
        //get으로 보내면 json이 아닌 parm 형식으로 나감
        type: "post",
        url: "/nickNameDuplicationCheck",
        data: JSON.stringify(memberCreateDto),
        contentType : "application/json; charset=utf-8",
        dataType: "json",

        success: function (result) {
            if (result == 0) {
                if (confirm("이 닉네임은 사용 가능합니다. \n사용하시겠습니까?")) {
                    usernameOverlapCheck = 1;
                    $("#Nickname").attr("readonly", true);
                    $("#NicknameOverlay").attr("disabled", true);
                    $("#NicknameOverlay").css("display", "none");
                    $("#resetNickname").attr("disabled", false);
                    $("#resetNickname").css("display", "inline-block");
                }
                return false;
            } else if (result == 1) {
                alert("이미 사용중인 닉네임입니다.");
                memberCreateDto.focus();
            } else {
                alert("success이지만 result 값이 undefined 잘못됨");
            }
        },
        error: function (request, status,error) {
            alert("ajax 실행 실패");
            alert("code:" + request.status + "\n" + "error :" + error);
        }
    });

}