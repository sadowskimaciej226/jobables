package pl.joble.feature;

public interface SampleJobOfferResponse {

    default String bodyWithTwoOffersJson(){
        return """
                [
                {
                "title": "Junior Java Developer",
                        "company": "BlueSoft Sp. z o.o.",
                        "salary": "7 000 – 9 000 PLN",
                        "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-bluesoft-remote-hfuanrre"
                    },
                    {
                        "title": "Java (CMS) Developer",
                        "company": "Efigence SA",
                        "salary": "16 000 – 18 000 PLN",
                        "offerUrl": "https://nofluffjobs.com/pl/job/java-cms-developer-efigence-warszawa-b4qs8loh"
                 }
                 ]
                """.trim();
    }
    default String bodyWithFourOffersJson(){
        return """
                [
                {
                "title": "Junior Java Developer",
                        "company": "BlueSoft Sp. z o.o.",
                        "salary": "7 000 – 9 000 PLN",
                        "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-bluesoft-remote-hfuanrre"
                    },
                    {
                    "title": "Mid Java Developer",
                        "company": "BlueSoft Sp. z o.o.",
                        "salary": "9 000 – 12 000 PLN",
                        "offerUrl": "https://pracuj.pl/pl/job/junior-java-developer-bluesoft-remote-hfuanrre"
                    },
                    {
                    "title": "Senior Java Developer",
                        "company": "BlueSoft Sp. z o.o.",
                        "salary": "12 000 – 15 000 PLN",
                        "offerUrl": "https://justjoin.it/pl/job/junior-java-developer-bluesoft-remote-hfuanrre"
                    },
                    {
                        "title": "Java (CMS) Developer",
                        "company": "Efigence SA",
                        "salary": "16 000 – 18 000 PLN",
                        "offerUrl": "https://nofluffjobs.com/pl/job/java-cms-developer-efigence-warszawa-b4qs8loh"
                 }
                 ]
                """.trim();
    }
    default String bodyWithZeroOffersJson(){
        return "[]";
    }
}
