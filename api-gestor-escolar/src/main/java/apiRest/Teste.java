package apiRest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import apiRest.modelo.aluno.Aluno;



public class Teste {
	
	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		Teste http = new Teste();
		String chamadaWS;

		chamadaWS = "http://localhost:9091/api-gestor-escolar/api/aluno/list";
		String json = http.sendGet(chamadaWS);
		Gson g = new Gson();

		Type usuarioType = new TypeToken<Aluno>() {
		}.getType();

		List<Aluno> alunos = g.fromJson(json, usuarioType);

		for(Aluno aluno : alunos){
			System.out.println(aluno.getNome());
		}
	}
	
	
	// HTTP GET request
		private String sendGet(String url) throws Exception {
	 
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			con.setRequestMethod("GET");
	 
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
	 
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			return response.toString();
	 
		}

}
