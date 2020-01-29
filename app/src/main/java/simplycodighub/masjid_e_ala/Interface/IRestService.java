package simplycodighub.masjid_e_ala.Interface;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import simplycodighub.masjid_e_ala.Model.AttendResponse;
import simplycodighub.masjid_e_ala.Model.AttendTrackActivityResponse;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse0;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse1;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse2;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse3;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse4;
import simplycodighub.masjid_e_ala.Model.AttendanceListResponse5;
import simplycodighub.masjid_e_ala.Model.CountResponse;
import simplycodighub.masjid_e_ala.Model.EditProfileResponse;

import simplycodighub.masjid_e_ala.Model.PaymentResponse;
import simplycodighub.masjid_e_ala.Model.RegisterResponse;
import simplycodighub.masjid_e_ala.Model.StudentListResponse;

public interface IRestService {
    @FormUrlEncoded
    @POST("/masjid/register.php")
     Call<RegisterResponse> getRegister(@Field("student_name") String student_name,
                                        @Field("father_name") String father_name,
                                        @Field("father_mobile") String father_mobile,
                                        @Field("gender") String gender,
                                        @Field("address") String address,
                                        @Field("course") String course,
                                        @Field("alt_mobile") String alt_mobile,
                                        @Field("fees") String fees,
                                        @Field("section_class") String section_class,
                                        @Field("student_type") String student_type,
                                        @Field("shift") String shift);

    @FormUrlEncoded
    @POST("/masjid/update_payment.php")
    Call<PaymentResponse> getPayment(@FieldMap Map<String, String> options);


    @FormUrlEncoded
   @POST("/masjid/update_edit_profile.php")
    Call<EditProfileResponse> getEditProfile(@FieldMap Map<String, String> options);

    @GET("/masjid/all_student_list.php")
    Call<List<StudentListResponse>> getStudentList();

    @GET("/masjid/emp_attendance0.php")
    Call<List<AttendanceListResponse0>> getAttendanceList0();

    @GET("/masjid/emp_attendance.php")
    Call<List<AttendanceListResponse1>> getAttendanceList1();


    @GET("/masjid/emp_attendance2.php")
    Call<List<AttendanceListResponse2>> getAttendanceList2();

    @GET("/masjid/emp_attendance3.php")
    Call<List<AttendanceListResponse3>> getAttendanceList3();

    @GET("/masjid/emp_attendance4.php")
    Call<List<AttendanceListResponse4>> getAttendanceList4();

    @GET("/masjid/emp_attendance5.php")
    Call<List<AttendanceListResponse5>> getAttendanceList5();

    @GET("/masjid/AttendanceFilter.php")
    Call<List<AttendTrackActivityResponse>> getAttendTrackList();


    @POST("/masjid/std_count.php")
    Call<CountResponse> getStudentCount();


    @FormUrlEncoded
    @POST("/masjid/update_attend.php")
    Call<AttendResponse> getAttend(@Field("std_id") String std_id,
                                   @Field("status") String status);




}