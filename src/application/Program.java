package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
        //classes básicas para operação com JDBC sendo instanciadas
        //Connection representa a conexão com o BD
        Connection conn = null;
        //Statemente executa consultas SQL
        Statement st = null;
        //ResultSet armazena e manipula os resultados da consulta SQL realizada pelo Statement
        ResultSet rs = null;
        try {
            //Classe Connection sendo instanciada com o método que cria a conexão com o BD
            conn = DB.getConnection();

            //Criação do Statement para que a consulta SQL seja executada. só pode ser criado a partir de uma conexão.
            st = conn.createStatement();

            //Execução do select pelo Statement e ResultSet rs armazenando o resultado da query.
            rs = st.executeQuery("select * from department");

            //manipulação do resultado. método next percorre os registros
            while (rs.next()) {
                //métodos getInt e getString extraem os dados.
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        //bloco para garantir o fechamento dos recursos e evitar vazamento de memória
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}