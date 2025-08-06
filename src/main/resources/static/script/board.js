function idcheck() {
    if(document.join.userid.value == '') {
        alert("아이디를 입력하세요.");
        return;
    }

    var inputid = document.join.userid.value;
    var opt = "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=500, height=200";
    window.open('idcheck?userid=' + inputid, 'idcheck', opt);
}

function deleteMember(userid) {
    if(confirm('저장된 회원 정보 및 게시물, 댓글이 전부 삭제됩니다. 계속하시겠습니까?')) {
        location.href='deleteMember?userid=' + userid;
    }
}

function selectImg() {
    var opt = 'toolbar=no, menubar=no, resizable=no, width=450, heigth=200';
    window.open('selectimg', 'selectimg', opt)
}

function deleteBoard(num) {
    if(confirm('해당 게시물과 댓글이 삭제됩니다. 계속하시겠습니까?')) {
        var pass = prompt('게시물의 비밀번호를 입력하세요.', '')
        location.href='deleteBoard?num=' + num + '&pass=' + pass;
    }
}

function replyCheck() {
    if(document.addRep.content.value == '') {
        alert('댓글을 입력하세요.');
        return false;
    }
    return true;
}

function deleteReply(replynum, boardnum) {
    if(confirm('댓글을 삭제하시겠습니까?')) {
        location.href='deleteReply?replynum=' + replynum + '&boardnum=' + boardnum;
    }
}

function updatePwdForm(num) {
    var opt = 'toolbar=no, menubar=no, resizable=no, width=450, heigth=200';
    window.open('updatePwdForm?num=' + num, 'updatePwd', opt);
}