import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class userInfo {
    public static void main(String[] args) throws IOException, InterruptedException {
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjkxN2U2ZmNhLWY4ZDUtNDcxMS1iM2M2LTM5NDk4NGM5ZmRjNiIsImlhdCI6MTc0MTQ2Mzk4Miwic3ViIjoiZGV2ZWxvcGVyL2I3OWRlZjNlLTgzZWEtYjJlZi05ZGY3LWQ5ZDVjYzZmMDUzMiIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyIyMDEuNDIuMTExLjE5MSJdLCJ0eXBlIjoiY2xpZW50In1dfQ.QeBq-R5zdtgldaTvOo7nG5fECkXBzAlNocW0EcgZftLJO1TGysTLt0QhzbRQfXi9MVUl5cAfwxr8r3dbvVOCVA";

        Scanner leitor = new Scanner(System.in);
        System.out.println("Digite o seu ID do Clash Royale para verificar as infomações da sua conta e os próximos baús (sem o #): ");
        String clashId = leitor.nextLine();


        HttpClient client = HttpClient.newHttpClient();

        HttpRequest requestUserInfo = HttpRequest.newBuilder()
                .uri(URI.create("https://api.clashroyale.com/v1/players/%23" + clashId))
                .header("Authorization", token)
                .header("Accept", "application/json")
                .build();

        HttpRequest requestUpcomingChests = HttpRequest.newBuilder()
                .uri(URI.create("https://api.clashroyale.com/v1/players/%23"+clashId+"/upcomingchests"))
                .header("Authorization", token)
                .header("Accept", "application/json")
                .build();

        HttpResponse responseUser = client
                .send(requestUserInfo, HttpResponse.BodyHandlers.ofString());

        HttpResponse responseChest = client
                .send(requestUpcomingChests, HttpResponse.BodyHandlers.ofString());


        String userJson = (String) responseUser.body();
        String chestsJason = (String) responseChest.body();

        Gson gson = new Gson();





        if (responseUser.statusCode() == 200){
            Account account = gson.fromJson(userJson, Account.class);
            ChestList chestList = gson.fromJson(chestsJason, ChestList.class);

            System.out.println("Tag: " + account.getTag() + "\nUser: " + account.getName() + "\nExp Leval: " + account.getExpLevel() + "\nTroféus: " + account.getTrophies());

            for (nextChest nextChest : chestList.getItems()){
                System.out.println(nextChest);
            }

        } else {
            System.out.println("Erro: "+ responseUser.statusCode() + "(" + responseUser.body() + ")");
        }



    }
}
