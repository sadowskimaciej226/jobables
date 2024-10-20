package pl.joble.feature;

import org.junit.jupiter.api.Test;
import pl.joble.BaseIntegrationTest;

public class HappyPathTestIntegration extends BaseIntegrationTest {

    @Test
    public void should_user_get_offers(){
//        1.	Scheduler runs for the 1st time and made GET to server to get All joboffers
//        2.	Scheduler made Get to server every 3 hours
//        4.	System validates data and return 200 OK with JWT token if validation is successful or 400 BAD REQUEST if for example there is already user with that email
//        5.	If account already exists user can POST /login with email and password to get jwt token, system can return 200OK with JWT or 400 BAD REQUEST if password is wrong
//        6.	User with JWT(and in any endpoint except /login and /singin) can GET /joboffers,
//        7.	 Job offers are saved in ConcurrentHashmap to cache data (all data are removed from hashmap every 60 min from request)
//        8.	User can save joboffer on POST /joboffer

    }
}
