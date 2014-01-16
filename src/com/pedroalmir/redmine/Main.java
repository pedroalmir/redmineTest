package com.pedroalmir.redmine;

import java.util.HashMap;
import java.util.Map;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Issue;

/**
 * 
 * @author Pedro Almir
 *
 */
public class Main {
	private static String redmineHost = "http://redmine.killline.com/";
	/* Removi minha chave para não gerar nenhuma polêmica.
	 * Qualquer dúvida me avisa.*/
	private static String apiAccessKey = "";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/* RedmineManager: Resposável por realizar as ações do Redmine. Este que você está
			 * utilizando é uma versão que estou trabalhando. Você precisa informar o host do 
			 * Redmine e de uma API de acesso. Essa AccessKey você encontra na página 'Minha Conta'.
			 * */
			RedmineManager mgr = new RedmineManager(redmineHost, apiAccessKey);
			/* Bem, como você queria acessar apenas as tarefas com uma situação especifica eu utilizei
			 * um mapa de parâmetros que serão utilizados como filtros. Para saber quais parâmetros podem
			 * ser utilizandos acesse: http://www.redmine.org/projects/redmine/wiki/Rest_api.
			 * */
			Map<String, String> parametros = new HashMap<String, String>();
			/* Aqui estou filtrando por todas as tarefas com status_id igual a 3, ou seja, com o estado 
			 * Resolvida no KillLine. Lembre que esses valores podem mudar de Redmine para o outro.
			 * */
			parametros.put("status_id", new Integer(3).toString());
			for(Issue issue : mgr.getIssues(parametros)){
				System.out.println(issue.toString());
			}
		} catch (RedmineException e) {
			e.printStackTrace();
		}
	}
}
