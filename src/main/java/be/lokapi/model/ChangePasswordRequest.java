package be.lokapi.model;



public class ChangePasswordRequest extends ChangePasswordDTO{
    public ChangePasswordRequest(String oldPassword, String newPasword,String confirmPassword) {
        super(oldPassword,newPasword, confirmPassword);
    }


    public static ChangePasswordRequest fromDTO(ChangePasswordDTO dto) {
        return new ChangePasswordRequest(dto.getOldPassword(),dto.getNewPassword(),dto.getConfirmPassword());
    }
}
