$('#signUpForm-submit').on('click', function() {
    $.ajax({
        url: '/membersystem/checkValidation',
        type: 'POST',
        dataType: 'json',
        data: {
            email: $('#sign-up-email').val(),
            memberName: $('#sign-up-membername').val(),
            password: $('#sign-up-password').val()

        },
        success: function(res) {


            if (res.isPasswordValid == 0) {
                $('#small-password').html("請設定6個字元以上，首字母為英文的密碼").css("color", "red");
            } else {
                $('#small-password').hide();
            }
            if (res.isEmailValid == 0) {
                $('#small-email').html("請設定正確的電子信箱").css("color", "red");
            } else {
                $('#small-email').hide();
            }
            if (res.isMemberNameValid == 0) {
                $('#small-member').html("請設定4個字元以上，首字母為英文的帳號").css("color", "red");
            } else {
                $('#small-member').hide();
            }

            if (res.isMemberValid == 1) {
                $('#submit-form').click();
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
$('#sign-up-password').on('blur', confirmPassword);
$('#confirm-password').on('blur', confirmPassword);
