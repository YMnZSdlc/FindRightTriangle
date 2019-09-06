package pl.ymz.righttriangle.service;

import pl.ymz.righttriangle.model.Point;
import pl.ymz.righttriangle.model.Triangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        List<Point> staticTestList = new ArrayList<>();
        Point a = new Point("A", 4, 2);
        Point b = new Point("B", 7, 2);
        Point c = new Point("C", 4, 6);
        staticTestList.add(a);
        staticTestList.add(b);
        staticTestList.add(c);

        List<Point> randomTestList = new ArrayList<>();

        randomTestList = makeRandomList(40); //min 3 punkty,

//        printPointList(randomTestList);

//        System.out.println(lenghtBetwenPoints(staticTestList.get(1), staticTestList.get(2)));

//        findRightTriangle(staticTestList);

        printTriangleList(findRightTriangle(randomTestList));
    }

    private static List<Triangle> findRightTriangle(List<Point> pointList) {
        List<Triangle> result = new ArrayList<>();
        for (int a = 0; a < pointList.size() - 2; a++) {
            for (int b = a + 1; b < pointList.size() - 1; b++) {
                for (int c = b + 1; c < pointList.size(); c++) {
                    Double aB, bC, cA;
                    List<Double> triangle = new ArrayList<>();
                    aB = lenghtBetwenPoints(pointList.get(a), pointList.get(b));
                    bC = lenghtBetwenPoints(pointList.get(b), pointList.get(c));
                    cA = lenghtBetwenPoints(pointList.get(c), pointList.get(a));
                    triangle.add(aB);
                    triangle.add(bC);
                    triangle.add(cA);
                    if (testPythagoras(triangle)) {
                        Triangle element = new Triangle();
                        element.setFirsrVertex(pointList.get(a));
                        element.setSecondVertex(pointList.get(b));
                        element.setThirdVertex(pointList.get(c));
                        result.add(element);
                    }
                }
            }
        }
        return result;
    }

    private static boolean testPythagoras(List<Double> triangleSides) {
        Collections.sort(triangleSides);
        if (triangleSides.get(0) * triangleSides.get(0) + triangleSides.get(1) * triangleSides.get(1)
                == triangleSides.get(2) * triangleSides.get(2)) {
            return true;
        } else return false;
    }

    private static void printTriangleList(List<Triangle> triangleList) {
        for (Triangle triangle : triangleList) {
            System.out.println(triangle);
        }
    }

    private static void printPointList(List<Point> pointList) {
        for (Point point : pointList) {
//            System.out.println(point);
            System.out.println(point.getCharName() + "(" + point.getX() + "," + point.getY() + ")");
        }
    }

    private static Double lenghtBetwenPoints(Point first, Point second) {
        Double result;
        result = Math.pow(Math.abs(first.getX() - second.getX()), 2) + Math.pow(Math.abs(first.getY() - second.getY()), 2);
        return Math.sqrt(result);
    }

    private static List<Point> makeRandomList(int pointNumber) {
        if (pointNumber < 3) {
            System.out.println("Liczba generowanych punktów powinna wynosić minimum 3");
            return null;
        }

        ArrayList<Point> randomList = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 0; i < pointNumber; i++) {
            Point point = new Point();

            point.setCharName(pointName(i));

            point.setX(rnd.nextInt(200) - 100);
            point.setY(rnd.nextInt(200) - 100);
            randomList.add(point);
        }
//        char charNameForPoint = (char) (rnd.nextInt(26) + 'a');
        return randomList;
    }

    private static String pointName(int i) {
        String pointName = null;
        return pointNameRecu(pointName, i);
    }

    private static String pointNameRecu(String partName, int i) {
        if (i < 26) {
            partName = "" + (char) ('A' + i);
            return partName;
        } else {
            partName = "" + (char) ('A' + i % 26);
            return pointNameRecu(partName, i / 26 - 1) + partName;
        }
    }


}
