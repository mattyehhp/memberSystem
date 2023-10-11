$('#signUpForm-submit').on('click', function() {

    $.ajax({
        url: '/membersystem/checkValidation',
        type: 'POST',
        dataType: 'json',
        data: {
            email: $('#sign-up-email').val(),
            memberName: $('#sign-up-membername').val(),
            password: $('#sign-up-password').val(),
            kaptcha: $('#kaptcha').val()
        },
        success: function(res) {


            if (res.isPasswordValid == 0) {
                $('#small-password').html("請設定6個字元以上，首字母為英文的密碼").css("color", "red").show();
            } else {
                $('#small-password').hide();
            }
            if (res.isEmailValid == 0) {
                $('#small-email').html("請設定正確的電子信箱").css("color", "red").show();
            } else {
                $('#small-email').hide();
            }
            if (res.isMemberNameValid == 0) {
                $('#small-member').html("請設定4個字元以上，首字母為英文的帳號").css("color", "red").show();
            } else {
                $('#small-member').hide();
            }
            if (res.isVerifyCodeCorrect == 0) {
                alert("Verify code is not correct!");
            }

            if (res.isMemberValid == 1 && res.isVerifyCodeCorrect == 1) {
                $.ajax({
                    url: '/membersystem/member/isUsed',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        email: $('#sign-up-email').val(),
                        memberName: $('#sign-up-membername').val()
                    },
                    success: function(res) {
                        if (res.isEmailUsed == 1) {
                            $('#small-email').html("Email已被使用.").css("color", "red").show();
                        } else {
                            $('#small-email').hide();
                        }
                        if (res.isMemberNameUsed == 1) {
                            $('#small-member').html("Member name已被使用").css("color", "red").show();
                        } else {
                            $('#small-member').hide();
                        }
                        if (res.isEmailUsed == 0 && res.isMemberNameUsed == 0) {
                            $('#submit-form').click();
                        }
                    },
                    error: function(error) {
                        console.log(error);
                    }

                });
            }
        },
        error: function(error) {
            console.log(error);
        }

    });

});


function confirmPassword() {
    if ($('#sign-up-password').val() == $('#confirm-password').val()){
        console.log("password is correct.");
        $('#small-confirm-password').html("Password is correct.");
        $('#small-confirm-password').css("color", "green");
    } else {
        console.log("password is wrong.");
        $('#small-confirm-password').html("Password is wrong.");
        $('#small-confirm-password').css("color", "red");
    }
}

function changeVerifyImgByLink() {

    const pathName = document.location.pathname;
    console.log(pathName.indexOf("/"));
    const index = pathName.substring(1).indexOf("/");
    const context = pathName.substring(0, index+1);

    $('#kaptchaImg').hide().attr('src', context + '/getVerifyImage?' + Math.floor(Math.random()*100)).fadeIn();
}

$('#sign-up-password').on('blur', confirmPassword);
$('#confirm-password').on('blur', confirmPassword);
$('#kaptchaLink').on('click', changeVerifyImgByLink);
