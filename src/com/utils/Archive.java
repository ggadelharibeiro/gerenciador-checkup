package com.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Archive {


    public Archive(){
    }

    public static String Read(String path){
        String conteudo = "";
        try {
            FileReader arq = new FileReader(path);
            BufferedReader read = new BufferedReader(arq);
            String line="";
            try {
                line = read.readLine();
                while(line!=null){
                    conteudo += line+"\n";
                    line = read.readLine();
                }
                arq.close();
                return conteudo;
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return "no";
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return "no";
        }
    }

    public static String ReadSingleLine(String path){
        String conteudo = "";
        try {
            FileReader arq = new FileReader(path);
            BufferedReader read = new BufferedReader(arq);
            String line="";
            try {
                line = read.readLine();
                while(line!=null){
                    conteudo += line;
                    line = read.readLine();
                }
                arq.close();
                return conteudo;
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return "no";
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return "no";
        }
    }

    public static void WriteAgenda(String path, String id) {
        try {

            FileWriter arq = new FileWriter(path);
            try (PrintWriter gravar = new PrintWriter(arq)) {
                gravar.print(id);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriteProntuario(int id, String path, String nome, String patologia, String data_nasc, String ultima_prescricao, String posologia) {
        try {

            FileWriter arq = new FileWriter(path);
            try (PrintWriter gravar = new PrintWriter(arq)) {
                gravar.print(id + ";" + nome + ";" + patologia + ";" + data_nasc + ";" + ultima_prescricao + ";" + posologia);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriteTarefas(String path, String task) {
        try {

            FileWriter arq = new FileWriter(path);
            try (PrintWriter gravar = new PrintWriter(arq)) {
                gravar.print(task);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String FormatarHorario(String horario){
        return horario.toLowerCase();
    }

    public static void DeletarArquivo(String path){
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
