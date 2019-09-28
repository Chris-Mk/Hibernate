package app.ccb.domain.dtos;

import com.google.gson.annotations.SerializedName;

public class ClientImportDto {

    @SerializedName(value = "first_name")
    private String firstName;

    @SerializedName(value = "last_name")
    private String lastName;

    private int age;

    @SerializedName(value = "appointed_employee")
    private String appointedEmployee;

    public ClientImportDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAppointedEmployee() {
        return appointedEmployee;
    }

    public void setAppointedEmployee(String appointedEmployee) {
        this.appointedEmployee = appointedEmployee;
    }
}
