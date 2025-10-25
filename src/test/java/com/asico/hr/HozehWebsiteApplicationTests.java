package com.asico.hr;

import com.asico.hr.payment.service.OrderService;
import com.asico.hr.payment.service.PaymentService;
import com.asico.hr.service.CartService;
import com.asico.hr.service.CourseService;
import com.asico.hr.service.UserService;
import com.asico.hr.sms.service.ISmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HozehWebsiteApplicationTests {



    @Autowired
    PaymentService paymentService;

    @Autowired
    CartService cartService;
    @Autowired
    CourseService courseService;

    @Autowired
    OrderService orderService;

    @Autowired
    ISmsService iSmsService;

    @Autowired
    UserService userService;


    @Test
    void testsms(){
//
//        UserModel userModel=userService.search("09034856387");
//        System.out.println(userModel.getName());
//      iSmsService.sendWelcomeCourseSmsAsync("09034856387", URLEncoder.encode(userModel.getName()));

    }


//    @Test
//    void orderService(){
//
//        List<CartModel> carts = cartService.search("09128431474");
//        ArrayList<CourseModel> UsercourseList = new ArrayList<>();
//
//        carts.forEach(course -> {
//            CourseModel courseDetails = courseService.findByCourseId(course.getCourseId());
//            UsercourseList.add(courseDetails);
//        });
//        System.out.println(UsercourseList.toString());
//
//        long totalPayPrice = cartService.calculateTotalAfterDiscount(UsercourseList) * 10;
//
//        String description="";
//        for (CourseModel courseModel:UsercourseList) {
//
//            description+=courseModel.getName()+"||";
//        }
//        System.out.println(description);
//        System.out.println(totalPayPrice);
//        AuthResponse response = paymentService.getAuthority(totalPayPrice, "09128431474", description,
//                "info@javabox.ir");
//        System.out.println(response.toString());
//    }

//    @Test
//    void getTotalPrice(){
//
//        List<CartModel> carts = cartService.search("09128431474");
//
//        ArrayList<CourseModel> UsercourseList = new ArrayList<>();
//
//        carts.forEach(course -> {
//            CourseModel courseDetails = courseService.findByCourseId(course.getCourseId());
//            UsercourseList.add(courseDetails);
//        });
//
//        long totalPayPrice = cartService.calculateTotalAfterDiscount(UsercourseList) * 10;
//        System.out.println(totalPayPrice);
//    }


//    @Test
//    void authKey(){
//
//
//        AuthResponse response=paymentService.getAuthority(11000,"09128431474","دوره جاوا مقدماتی","info@grrenbox.com");
//
//        System.out.println( response.getData().getAuthority());
//        System.out.println(response.getData().getCode());
//        System.out.println(response.getData().getMessage());
//    }

//    @Test
//    void verification(){
//
//
//        ReverseResponse response=paymentService.paymentVerify(11000,"A000000000000000000000000000wp1qxwgg");
//
//        System.out.println( response.getData().getCard_pan());
//        System.out.println(response.getData().getCode());
//        System.out.println(response.getData().getMessage());
//    }
//
//    @Autowired
//    UserService userService;
//    @Autowired
//    CartService cartService;
//
//    @Autowired
//    UserCourseService userCourseService;
//
//    @Autowired
//    CourseService CourseService;
//
//
//    @Autowired
//    License license;
//
//    @Autowired
//    CertificateService certificateService;
//
//    @Autowired
//    ExamService examService;
//
//
//    @Test
//    void deletefromCart(){
//
//
//        System.out.println(cartService.delete("09128431474","CS106"));
//    }
//
//    @Test
//    void exam() {
//
//        List<CourseModel> list =new ArrayList<>();
//        CourseModel courseModel = CourseService.findByCourseId("CS105");
//        CourseModel courseModel1 = CourseService.findByCourseId("CS106");
//        list.add(courseModel);
//        list.add(courseModel1);
//
//        long totalPrice = list.stream().mapToLong(CourseModel::getDiscountPrice).sum();
//        //System.out.println(CourseService.findByCoursePage("/javase"));
//        System.out.println(totalPrice);
//
//    }
//
//    @Test
//    void certificateCount() {
//
//
//        List<CertificateModel> list = certificateService.search("09128431474");
//
//
//        int count = certificateService.countNumberOfCertificateNotIssue("09128431474");
//
//        System.out.println(count);
//    }
//
//    @Test
//    void issuecertificateCount() {
//
//
//        int count = certificateService.issueCertificate("09128431474", "CS104");
//
//        System.out.println(count);
//    }
//
//    @Test
//    void generateJson() throws JSONException {
//        OkHttpClient client = new OkHttpClient();
//
//// Constructing the JSON object dynamically
//        JSONObject jsonBody = new JSONObject();
//        try {
//            jsonBody.put("test", true);
//            JSONArray courseArray = new JSONArray();
//            courseArray.put("638b2e04faf9a3f34b997777"); // Add your dynamic course value here
//            jsonBody.put("course", courseArray);
//            jsonBody.put("name", "ali");
//
//            JSONObject watermark = new JSONObject();
//            JSONArray textsArray = new JSONArray();
//            JSONObject textObj = new JSONObject();
//            textObj.put("text", "09121112266");
//            textsArray.put(textObj);
//            watermark.put("texts", textsArray);
//
//            jsonBody.put("watermark", watermark);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        MediaType mediaType = MediaType.parse("application/json");
//
//        RequestBody body = RequestBody.create(mediaType, jsonBody.toString());
//
//        Request request = new Request.Builder()
//                .url("https://panel.spotplayer.ir/license/edit/")
//                .method("POST", body)
//                .addHeader("$API", "Y4sgKaO7a1IdMF40i9+GvVGqiAQy9w==")
//                .addHeader("$LEVEL", "-1")
//                .addHeader("Content-Type", "application/json")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response.body().string());
//            // Handle response here
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    @Test
//    void license() {
//        LicenseModel licenseModel = license.generateLicense("CS104", "09128431474");
//
//        System.out.println(licenseModel.getKey());
//        System.out.println(licenseModel.get_id());
//        System.out.println(licenseModel.getUrl());
//    }
//
//    @Test
//    void course() {
//
//        System.out.println(userCourseService.getAll("09128431474"));
//
//
//        List<CourseModel> courseModels = new ArrayList<>();
//        List<UserCourseModel> list = userCourseService.getAll("09128431474");
//        list.forEach(a -> {
//            courseModels.add(CourseService.search(a.getCourseId()));
//        });
//
//        System.out.println("=========================");
//        System.out.println(courseModels);
//    }
//
//
//    @Test
//    void cart() {
//
//        CartModel model = new CartModel();
//        model.setPhoneNumber("09128431474");
//        model.setCourseId("8545122");
//
//        CartModel model1 = new CartModel();
//        model1.setPhoneNumber("09128431474");
//        model1.setCourseId("85451eerrr22");
//
//        CartModel model2 = new CartModel();
//        model2.setPhoneNumber("09123909046");
//        model2.setCourseId("8545122");
//
//        CartModel model3 = new CartModel();
//        model2.setPhoneNumber("09123909046");
//        model2.setCourseId("8545122dsff");
//
//        cartService.saveAsync(model);
//        cartService.saveAsync(model1);
//        cartService.saveAsync(model2);
//        cartService.saveAsync(model3);
//    }
//
//    @Test
//    void cartget() {
//        cartService.search("09128431474");
//    }
//    @Test
//    void contextLoads() {
//
//        userModel userModel = userService.search("09128431474");
//        if (userModel == null) {
//
//            System.out.println("nulllll");
//        }
//
//    }
//
//    @Test
//    void contextLoads2() {
//
//
//        userModel model = new userModel();
//        model.setPhoneNumber("09128431474");
//        model.setName("09128431474");
//        userService.save(model);
//
//    }
//
//    @Test
//    void contextLoads3() {
//
//        userService.updatephone("09123909046", "09128431474");
//    }


}
