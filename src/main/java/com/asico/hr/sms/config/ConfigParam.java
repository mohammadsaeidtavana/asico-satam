package com.asico.hr.sms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author m.tavana
 * @since 2025\
 */

@Configuration
public class ConfigParam {

    @Value("${sms.default.otp.optTemplate}")
    public   String optDefaultTemplate;
    @Value("${sms.welcome.course.template}")
    public   String smsWelcomeTemplate;

    @Value("${sms.otp.optMessageSend}")
    public   String optMessageSend;
    @Value("${sms.default.otp.optTemplate}")
    public   String optMessageHeader;
    @Value("${sms.otp.websiteUrl}")
    public   String websiteUrl;
    @Value("${sms.otp.ghasedak.management.otpWebsiteUrl}")
    public   String otpWebsiteUrl;

    @Value("${sms.otp.apiKey}")
    public   String otpApiKey;
}
