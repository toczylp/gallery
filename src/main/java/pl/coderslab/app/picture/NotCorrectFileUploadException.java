package pl.coderslab.app.picture;

public class NotCorrectFileUploadException extends Exception {

    @Override
    public String getMessage() {
        return "Not Correct file: allowed only JPEG, BMP, TIFF, PNG";
    }
}
