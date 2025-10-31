package com.asico.hr.utils;

import com.asico.hr.domain.person.UserProfile;
import com.asico.hr.entity.person.*;
import com.asico.hr.service.FileUploadService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class UserProfileMapper {


     FileUploadService fileUploadService;

    private  List<Education> educationsDtoToEducation(List<com.asico.hr.domain.person.Education> educations, String codeMeli, UserProfileEntity userProfile) {

        if (educations.isEmpty())
            return new ArrayList<>();
        List<Education> educationsEntities = new ArrayList<>();
        int index=0;
        for (com.asico.hr.domain.person.Education education : educations) {
            Education educationEntity = new Education();
            educationEntity.setDegree(education.getDegree());
            educationEntity.setField(education.getField());
            educationEntity.setUniversity(education.getUniversity());
            educationEntity.setStartDate(education.getStartDate());
            educationEntity.setEndDate(education.getEndDate());
            educationEntity.setUserProfile(userProfile);
            if (education.getFile() != null && !education.getFile().isEmpty()) {
                String fileName = "education-" + index;
                educationEntity.setFile(fileUploadService.upload(education.getFile(), codeMeli, fileName, "educations"));
            }
            educationsEntities.add(educationEntity);
            index++;
        }
        return educationsEntities;
    }

    private  List<Course> coursesDtoToCourse(
            List<com.asico.hr.domain.person.Course> courses,
            String codeMeli,
            UserProfileEntity userProfile
    ) {
        if (courses == null || courses.isEmpty()) {
            return new ArrayList<>();
        }

        List<Course> courseEntities = new ArrayList<>();
        int index=0;
        for (com.asico.hr.domain.person.Course dto : courses) {
            Course entity = new Course();
            entity.setTitle(dto.getTitle());
            entity.setOrganizer(dto.getOrganizer());
            entity.setReceivedDate(dto.getReceivedDate());
            entity.setDuration(dto.getDuration());
            entity.setUserProfile(userProfile);

            // آپلود فایل در صورت وجود
            MultipartFile file = dto.getFile();
            if (file != null && !file.isEmpty()) {
                String fileName = "course-" + index;
                entity.setFile(fileUploadService.upload(file, codeMeli, fileName, "courses"));
            }

            courseEntities.add(entity);
            index++;
        }

        return courseEntities;
    }

    private  List<Experience> experiencesDtoToExperience(
            List<com.asico.hr.domain.person.Experience> experiences,
            UserProfileEntity userProfile
    ) {
        if (experiences == null || experiences.isEmpty()) {
            return new ArrayList<>();
        }

        List<Experience> experienceEntities = new ArrayList<>();
        for (com.asico.hr.domain.person.Experience dto : experiences) {
            Experience entity = new Experience();
            entity.setCompany(dto.getCompany());
            entity.setCity(dto.getCity());
            entity.setStartDate(dto.getStartDate());
            entity.setEndDate(dto.getEndDate());
            entity.setDuration(dto.getDuration());
            entity.setPosition(dto.getPosition());
            entity.setUserProfile(userProfile);
            experienceEntities.add(entity);
        }

        return experienceEntities;
    }

    private  List<Expertise> expertisesDtoToExpertise(
            List<com.asico.hr.domain.person.Expertise> expertises,
            UserProfileEntity userProfile
    ) {
        if (expertises == null || expertises.isEmpty()) {
            return new ArrayList<>();
        }

        List<Expertise> expertiseEntities = new ArrayList<>();
        for (com.asico.hr.domain.person.Expertise dto : expertises) {
            Expertise entity = new Expertise();
            entity.setField(dto.getField());
            entity.setIndustry(dto.getIndustry());
            entity.setOrientation(dto.getOrientation());
            entity.setUserProfile(userProfile);

            expertiseEntities.add(entity);
        }

        return expertiseEntities;
    }

    private  List<PersonalExperience> personalExperiencesDtoToEntity(
            List<com.asico.hr.domain.person.PersonalExperience> dtos,
            String codeMeli,
            UserProfileEntity userProfile
    ) {
        if (dtos == null || dtos.isEmpty()) {
            return new ArrayList<>();
        }

        List<PersonalExperience> entities = new ArrayList<>();
        int index=0;
        for (com.asico.hr.domain.person.PersonalExperience dto : dtos) {
            PersonalExperience entity = new PersonalExperience();
            entity.setCompanyName(dto.getCompanyName());
            entity.setStartDate(dto.getStartDate());
            entity.setEndDate(dto.getEndDate());
            entity.setUserProfile(userProfile);

            MultipartFile file = dto.getOfficialGazette();
            if (file != null && !file.isEmpty()) {
                String fileName = "PersonalExperience-" + index;
                entity.setOfficialGazetteLink(fileUploadService.upload(file, codeMeli, fileName, "PersonalExperiences"));
            }

            entities.add(entity);
            index++;
        }

        return entities;
    }

    private  List<Research> researchesDtoToEntity(
            List<com.asico.hr.domain.person.Research> dtos,
            UserProfileEntity userProfile
    ) {
        if (dtos == null || dtos.isEmpty()) {
            return new ArrayList<>();
        }

        List<Research> entities = new ArrayList<>();
        for (com.asico.hr.domain.person.Research dto : dtos) {
            Research entity = new Research();
            entity.setTitle(dto.getTitle());
            entity.setObjective(dto.getObjective());
            entity.setLink(dto.getLink());
            entity.setUserProfile(userProfile);

            entities.add(entity);
        }

        return entities;
    }

    private  List<Skill> skillsDtoToEntity(
            List<com.asico.hr.domain.person.Skill> dtos,
            UserProfileEntity userProfile
    ) {
        if (dtos == null || dtos.isEmpty()) {
            return new ArrayList<>();
        }

        List<Skill> entities = new ArrayList<>();
        for (com.asico.hr.domain.person.Skill dto : dtos) {
            Skill entity = new Skill();
            entity.setTechnical(dto.getTechnical());
            entity.setSoft(dto.getSoft());
            entity.setUserProfile(userProfile);

            entities.add(entity);
        }

        return entities;
    }


    public  UserProfileEntity userProfileDtoToEntity(
            com.asico.hr.domain.person.UserProfile dto
    ) {
        if (dto == null) return null;

        UserProfileEntity entity = new UserProfileEntity();

        // ------------------ فیلدهای ساده ------------------
        entity.setFullname(dto.getFullname());
        entity.setEmail2(dto.getEmail2());
        entity.setCodemeli(dto.getCodemeli());
        entity.setCodeshenasname(dto.getCodeshenasname());
        entity.setFathername(dto.getFathername());
        entity.setBDplace(dto.getBDplace());
        entity.setBDyear(dto.getBDyear());
        entity.setBDmonth(dto.getBDmonth());
        entity.setBDday(dto.getBDday());
        entity.setBDissuyear(dto.getBDissuyear());
        entity.setBDissumonth(dto.getBDissumonth());
        entity.setBDissuday(dto.getBDissuday());
        entity.setSex(dto.getSex());
        entity.setMilitary(dto.getMilitary());
        entity.setCity(dto.getCity());
        entity.setProvince(dto.getProvince());
        entity.setMariage(dto.getMariage());
        entity.setNationalityM(dto.getNationalityM());
        entity.setAddress(dto.getAddress());
        entity.setPost(dto.getPost());
        entity.setNationality(dto.getNationality());
        entity.setReligion(dto.getReligion());
        entity.setIsgoverment(dto.getIsgoverment());
        entity.setIsretired(dto.getIsretired());
        entity.setIsasico(dto.getIsasico());
        entity.setSandoghCompanyName(dto.getSandoghCompanyName());
        entity.setInsuranceHistoryCount(dto.getInsuranceHistoryCount());
        entity.setNotinsuranceCount(dto.getNotinsuranceCount());

        String codeMeli = dto.getCodemeli();

        // ------------------ آپلود فایل‌ها ------------------
        if (dto.getPicture() != null && !dto.getPicture().isEmpty()) {
            entity.setPicture(fileUploadService.upload(dto.getPicture(), codeMeli, "ProfilePicture",""));
        }
        if (dto.getBirthCertificateFile() != null && !dto.getBirthCertificateFile().isEmpty()) {
            entity.setBirthCertificateFile(fileUploadService.upload(dto.getBirthCertificateFile(), codeMeli, "BirthCertificateFile", ""));
        }
        if (dto.getBirthCertificatePage2File() != null && !dto.getBirthCertificatePage2File().isEmpty()) {
            entity.setBirthCertificatePage2File(fileUploadService.upload(dto.getBirthCertificatePage2File(), codeMeli, "BirthCertificatePage2File", ""));
        }
        if (dto.getBirthCertificatePage3File() != null && !dto.getBirthCertificatePage3File().isEmpty()) {
            entity.setBirthCertificatePage3File(fileUploadService.upload(dto.getBirthCertificatePage3File(), codeMeli, "BirthCertificatePage3File", ""));
        }
        if (dto.getNationalCardFrontFile() != null && !dto.getNationalCardFrontFile().isEmpty()) {
            entity.setNationalCardFrontFile(fileUploadService.upload(dto.getNationalCardFrontFile(), codeMeli, "NationalCardFrontFile", ""));
        }
        if (dto.getNationalCardBackFile() != null && !dto.getNationalCardBackFile().isEmpty()) {
            entity.setNationalCardBackFile(fileUploadService.upload(dto.getNationalCardBackFile(), codeMeli, "NationalCardBackFile", ""));
        }
        if (dto.getMilitaryCardFile() != null && !dto.getMilitaryCardFile().isEmpty()) {
            entity.setMilitaryCardFile(fileUploadService.upload(dto.getMilitaryCardFile(), codeMeli, "MilitaryCardFile", ""));
        }
        if (dto.getInsuranceHistoryFile() != null && !dto.getInsuranceHistoryFile().isEmpty()) {
            entity.setInsuranceHistoryFile(fileUploadService.upload(dto.getInsuranceHistoryFile(), codeMeli, "InsuranceHistoryFile", ""));
        }
        if (dto.getResumeFile() != null && !dto.getResumeFile().isEmpty()) {
            entity.setResumeFile(fileUploadService.upload(dto.getResumeFile(), codeMeli, "ResumeFile", ""));
        }

        // ------------------ تبدیل لیست‌ها ------------------
        entity.setEducations(educationsDtoToEducation(dto.getEducations(), codeMeli, entity));
        entity.setCourses(coursesDtoToCourse(dto.getCourses(), codeMeli, entity));
        entity.setExperiences(experiencesDtoToExperience(dto.getExperiences(), entity));
        entity.setSkills(skillsDtoToEntity(dto.getSkills(), entity));
        entity.setResearches(researchesDtoToEntity(dto.getResearches(), entity));
        entity.setExpertises(expertisesDtoToExpertise(dto.getExpertises(), entity));
        entity.setPersonalExperiences(personalExperiencesDtoToEntity(dto.getPersonalExperiences(), codeMeli, entity));

        return entity;
    }


    public  UserProfile entityToDto(UserProfileEntity entity) {
        if (entity == null) return null;

        UserProfile dto = new UserProfile();

        dto.setFullname(entity.getFullname());
        dto.setEmail2(entity.getEmail2());
        dto.setCodemeli(entity.getCodemeli());
        dto.setCodeshenasname(entity.getCodeshenasname());
        dto.setFathername(entity.getFathername());
        dto.setBDplace(entity.getBDplace());


        dto.setBDyear(entity.getBDyear());
        dto.setBDmonth(entity.getBDmonth());
        dto.setBDday(entity.getBDday());

        dto.setBDissuyear(entity.getBDissuyear());
        dto.setBDissumonth(entity.getBDissumonth());
        dto.setBDissuday(entity.getBDissuday());

        dto.setSex(entity.getSex());
        dto.setMilitary(entity.getMilitary());
        dto.setCity(entity.getCity());
        dto.setProvince(entity.getProvince());
        dto.setMariage(entity.getMariage());
        dto.setNationalityM(entity.getNationalityM());
        dto.setAddress(entity.getAddress());
        dto.setPost(entity.getPost());
        dto.setNationality(entity.getNationality());
        dto.setReligion(entity.getReligion());
        dto.setIsgoverment(entity.getIsgoverment());
        dto.setIsretired(entity.getIsretired());
        dto.setIsasico(entity.getIsasico());
        dto.setSandoghCompanyName(entity.getSandoghCompanyName());
        dto.setInsuranceHistoryCount(entity.getInsuranceHistoryCount());
        dto.setNotinsuranceCount(entity.getNotinsuranceCount());


        return dto;
    }





}
