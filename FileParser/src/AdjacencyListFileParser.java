package filereaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * this class can parse the content of a file into an adjacenyList as Map. This only works
 * with the input file, which contains the adjacency list representation of a simple undirected graph.
 * The first column in the file represents the vertex label, and the particular row 
 * (other entries except the first column) tells all the vertices that the vertex is adjacent to.
 * So for example, the 6th row looks like : "6   155 56  52  120 ......". 
 * This just means that the vertex with label 6 is adjacent to (i.e., shares an edge with) 
 * the vertices with labels 155,56,52,120,......,etchas 
 * 
 * The deliminator default show be \t tab between the integer in rows
 * 
 * @author Yingding Wang
 */
public class AdjacencyListFileParser {
    private static Integer label;
    private static String[] tmpStrArray;
    public static final String DELIMINATOR = "\\t";
    public static void printState(int[] array) {
        System.out.println("Number of Content Parsed is: " + array.length);
    }

    public static void toString(Map<Integer,Set<Integer>> adjacencyList) {
        for (Map.Entry<Integer, Set<Integer>> entry : adjacencyList.entrySet())
        {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public static Map<Integer, Set<Integer>> parseInput(File file, int limit, String deliminator) {
        // since the set is the structure for the incidenting head nodes of a tail
        // no redundant head set is allowed, which means no parallel edges in this structure.
        Map <Integer, Set<Integer>> adjacencyList = new HashMap<Integer, Set<Integer>>(); 
        if (limit != 0) {
            int breakCount = 0;
            try (BufferedReader bR = new BufferedReader(new FileReader(file))) {
                for (String line; (line = bR.readLine()) != null;) {
                    breakCount++;
                    if (breakCount > limit)
                        break;
                    tmpStrArray = line.trim().split(deliminator);
                    if (tmpStrArray.length != 0) {
                        // Use valueOf to return a Integer Object, parseInt to return int primitive
                        // If the string can't be passed, a number format exception will be raised
                        label = Integer.valueOf(tmpStrArray[0]);
                        // Initializing adjacencyNodes size without the label 
                        Set<Integer> adjacencyNodes = new HashSet<Integer>(); 
                        for (int i = 1; i<tmpStrArray.length; i++) {
                            // copy the adjacency string value to int value.
                            adjacencyNodes.add(Integer.valueOf(tmpStrArray[i]));
                        }
                        adjacencyList.put(label, adjacencyNodes); 
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        } else {
            try (BufferedReader bR = new BufferedReader(new FileReader(file))) {
                for (String line; (line = bR.readLine()) != null;) {
                    tmpStrArray = line.trim().split(deliminator);
                    if (tmpStrArray.length != 0) {
                        // Use valueOf to return a Integer Object, parseInt to return int primitive
                        label = Integer.valueOf(tmpStrArray[0]);
                        // Initializing adjacencyNodes size without the label 
                        Set<Integer> adjacencyNodes = new HashSet<Integer>(); 
                        for (int i = 1; i<tmpStrArray.length; i++) {
                            // copy the adjacency string value to int value.
                            adjacencyNodes.add(Integer.valueOf(tmpStrArray[i]));
                        }
                        adjacencyList.put(label, adjacencyNodes); 
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
        return adjacencyList;
    }    
}