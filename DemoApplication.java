package ru.skillbox.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.skillbox.demo.entity.*;
import ru.skillbox.demo.repository.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//создаем пользователя
	@Bean
	CommandLineRunner userJpa (UserRepository userRepository) {
		return (args)-> {
			User vasya = new User();
			User petya = new User();
			User tanya = new User();

			userRepository.save(vasya);
			userRepository.save(petya);
			userRepository.save(tanya);


			for (User user : userRepository.findAll()) {
				System.out.println(user);
			}
		};
	}

	//создаем инфо
	@Bean
	CommandLineRunner UserInfoJpa (UserInfoRepository UserInfoRepository){
		return (args)-> {
			UserInfo vasyaInfo = new UserInfo(1L,"Vasili", "Vasiliev","","M","01/01/2000","Moscow",false);
			UserInfoRepository.save(vasyaInfo);

			for (UserInfo userInfo : UserInfoRepository.findAll()) {
				System.out.println(userInfo);
			}
		};
	}
	//создаем контакты
	@Bean
	CommandLineRunner UserContactsJpa (UserContactsRepository UserContactsRepository){
		return (args)-> {
			UserContacts vasyaContacts = new UserContacts(1L, "Vasyan111", "+79999999","vasya111@mail.org");
			UserContactsRepository.save(vasyaContacts);

			for (UserContacts userContacts : UserContactsRepository.findAll()) {
				System.out.println(userContacts);
			}
		};
	}

//создаем картинку
	@Bean
	CommandLineRunner UserPicsJpa (UserPicsRepository UserPicsRepository) {
		return (args) -> {
			UserPics vasyaPic = new UserPics(1L, "//sss/ss/ggfgf.gif");
			UserPicsRepository.save(vasyaPic);

			for (UserPics userPics : UserPicsRepository.findAll()) {
				System.out.println(userPics);
			}
		};
	}

	//создаем текст
	@Bean
	CommandLineRunner UserTextsJpa (UserTextsRepository UserTextsRepository) {
		return (args) -> {
			UserText vasyaText = new UserText(1L, "в чащах юга жил-был цитрус");
			UserTextsRepository.save(vasyaText);

			UserText tanyaText = new UserText(3L, "да, но фальшивый экземпляр");
			UserTextsRepository.save(tanyaText);

			for (UserText userText : UserTextsRepository.findAll()) {
				System.out.println(userText);
			}
		};
	}


	//создаем подписку
	@Bean
	CommandLineRunner SubscriptionJpa (SubscriptionRepository SubscriptionRepository){
		return (args)-> {
			Subscription VasyaTanya = new Subscription( new SubscriptionId (1L, 3L), "follow", false);
			SubscriptionRepository.save(VasyaTanya);

			for (Subscription Subscription : SubscriptionRepository.findAll()) {
				System.out.println(Subscription);
			}

		};

	}

	//создаем скилы
	@Bean
	CommandLineRunner UserSkillsJpa (UserSkillsRepository UserSkillsRepository){
		return (args)-> {
			UserSkills Vasya1 = new UserSkills(1L, "python" );
			UserSkills Vasya2 = new UserSkills(1L, "skiing" );
			UserSkills Vasya3 = new UserSkills(1L, "cross-stich" );

			UserSkillsRepository.save(Vasya1);
			UserSkillsRepository.save(Vasya2);
			UserSkillsRepository.save(Vasya3);

			for (UserSkills UserSkills: UserSkillsRepository.findAll()) {
				System.out.println(UserSkills);
			}

			System.out.println(UserSkillsRepository.findAllByUserId(1L));

			UserSkillsRepository.deleteByUserIdAndUskill(1L, "python");

			System.out.println(UserSkillsRepository.findAllByUserId(1L));

		};

	}
}

