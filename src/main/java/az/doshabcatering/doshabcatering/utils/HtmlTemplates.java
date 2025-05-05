package az.doshabcatering.doshabcatering.utils;


public class HtmlTemplates {

    public static String userVerification(String otp) {
        return "<html><body>" +
                "<p>Salam,</p>" +
                "<p>E-mail ünvanınızı təsdiqləmək üçün aşağıdakı linkə klikləyin:</p>" +
                "<p style='font-size: 18px; font-weight: bold;'><a href='https://doshabcatering.az/api/auth/verify/" + otp + "'>🔐 Emaili Təsdiqlə</a></p>" +
                "<p>Əgər bu sorğunu siz etməmisinizsə, xahiş edirik bu mesajı nəzərə almayın.</p>" +
                "<br><p>Hörmətlə,<br>Doshabcatering komandası</p>" +
                "</body></html>";

    }

    public static String htmlResponse() {
        return "<html>" +
                "<head>" +
                "<meta http-equiv=\"refresh\" content=\"3;url=https://doshabcatering.az/\" />" +
                "</head>" +
                "<body>" +
                "<h1>Hesabınız uğurla təsdiqləndi!</h1>" +
                "<p>3 saniyə sonra ana səhifəyə yönləndiriləcəksiniz...</p>" +
                "</body>" +
                "</html>";

    }

    public static String passwordUpdate(String otp) {
        return "<html><body>" +
                "<p>Salam,</p>" +
                "<p>Parolu yeniləmək üçün aşağıdakı kodu müvafiq xanaya yazın:</p>" +
                "<p style='font-size: 18px; font-weight: bold;'>" + otp + "</p>" +
                "<p>Əgər bu sorğunu siz etməmisinizsə, xahiş edirik bu mesajı nəzərə almayın.</p>" +
                "<br><p>Hörmətlə,<br>Doshabcatering komandası</p>" +
                "</body></html>";
    }
}