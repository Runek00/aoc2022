package org.example;

public class Day14 {

    record Point(int x, int y){}

    static class InputArray {
        int[][] arr;
        int minx = Integer.MAX_VALUE;
        int maxx;
        int maxy;

        int counter = 0;

        public InputArray(String input) {
            for (String line : input.split("\n")) {
                for (String point : line.split(" -> ")){
                    String[] p = point.split(",");
                    minx = Math.min(minx, Integer.parseInt(p[0]));
                    maxx = Math.max(maxx, Integer.parseInt(p[0]));
                    maxy = Math.max(maxy, Integer.parseInt(p[1]));
                }
            }
            arr = new int[maxx+1-minx][maxy+1];
            for (String line : input.split("\n")) {
                Point pp = null;
                for (String point : line.split(" -> ")) {
                    Point p = pointFromString(point.split(","));
                    if (pp == null) {
                        pp = p;
                        continue;
                    }
                    if (pp.x() != p.x()) {
                        int y = p.y();
                        for (int x = Math.min(pp.x(), p.x()); x <= Math.max(pp.x(), p.x()); x++) {
                            arr[x-minx][y] = 1;
                        }
                    } else {
                        int x = p.x();
                        for (int y = Math.min(pp.y(), p.y()); y <= Math.max(pp.y(), p.y()); y++) {
                            arr[x-minx][y] = 1;
                        }
                    }
                    pp = p;
                }
            }

        }

        private Point pointFromString(String[] split) {
            return new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        Point step() {
            Point cur = new Point(500, -1);
            Point newPoint = new Point(500, 0);
            while(cur != newPoint) {
                cur = newPoint;
                newPoint = fall(cur);
                if (newPoint.x() > maxx || newPoint.x() < minx || newPoint.y() > maxy) {
                    return new Point(-1, -1);
                }
            }
            arr[cur.x-minx][cur.y] = 2;
            return cur;
        }

        private Point fall(Point cur) {
            if (cur.y >= maxy || cur.x < minx || cur.x > maxx || arr[cur.x-minx][cur.y+1] == 0){
                return new Point(cur.x, cur.y+1);
            }
            if (cur.x-1 < minx || arr[cur.x-1-minx][cur.y+1] == 0){
                return new Point(cur.x-1, cur.y+1);
            }
            if (cur.x+1 > maxx || arr[cur.x+1-minx][cur.y+1] == 0){
                return new Point(cur.x+1, cur.y+1);
            }
            return cur;
        }
    }

    static int aoc14(String input) {
        InputArray in = new InputArray(input);
        int counter = 0;
        while(!in.step().equals(new Point(-1, -1))) {
            counter++;
        }
        return counter;
    }

//    static int aoc14a(String input) {
//    }

    static String day14Input = """
            512,137 -> 522,137
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            514,162 -> 514,165 -> 513,165 -> 513,171 -> 526,171 -> 526,165 -> 519,165 -> 519,162
            507,50 -> 507,53 -> 501,53 -> 501,59 -> 516,59 -> 516,53 -> 511,53 -> 511,50
            530,159 -> 534,159
            472,109 -> 472,110 -> 488,110 -> 488,109
            481,88 -> 485,88
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            514,162 -> 514,165 -> 513,165 -> 513,171 -> 526,171 -> 526,165 -> 519,165 -> 519,162
            519,150 -> 519,140 -> 519,150 -> 521,150 -> 521,140 -> 521,150 -> 523,150 -> 523,141 -> 523,150 -> 525,150 -> 525,142 -> 525,150
            519,150 -> 519,140 -> 519,150 -> 521,150 -> 521,140 -> 521,150 -> 523,150 -> 523,141 -> 523,150 -> 525,150 -> 525,142 -> 525,150
            478,85 -> 482,85
            494,32 -> 498,32
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            488,32 -> 492,32
            494,28 -> 498,28
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            485,34 -> 489,34
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            491,26 -> 495,26
            484,85 -> 488,85
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            472,109 -> 472,110 -> 488,110 -> 488,109
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            507,50 -> 507,53 -> 501,53 -> 501,59 -> 516,59 -> 516,53 -> 511,53 -> 511,50
            491,34 -> 495,34
            498,121 -> 498,122 -> 509,122 -> 509,121
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            519,150 -> 519,140 -> 519,150 -> 521,150 -> 521,140 -> 521,150 -> 523,150 -> 523,141 -> 523,150 -> 525,150 -> 525,142 -> 525,150
            507,50 -> 507,53 -> 501,53 -> 501,59 -> 516,59 -> 516,53 -> 511,53 -> 511,50
            484,79 -> 488,79
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            507,125 -> 507,127 -> 505,127 -> 505,134 -> 514,134 -> 514,127 -> 513,127 -> 513,125
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            475,88 -> 479,88
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            507,125 -> 507,127 -> 505,127 -> 505,134 -> 514,134 -> 514,127 -> 513,127 -> 513,125
            514,162 -> 514,165 -> 513,165 -> 513,171 -> 526,171 -> 526,165 -> 519,165 -> 519,162
            514,162 -> 514,165 -> 513,165 -> 513,171 -> 526,171 -> 526,165 -> 519,165 -> 519,162
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            507,50 -> 507,53 -> 501,53 -> 501,59 -> 516,59 -> 516,53 -> 511,53 -> 511,50
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            519,150 -> 519,140 -> 519,150 -> 521,150 -> 521,140 -> 521,150 -> 523,150 -> 523,141 -> 523,150 -> 525,150 -> 525,142 -> 525,150
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            482,32 -> 486,32
            519,150 -> 519,140 -> 519,150 -> 521,150 -> 521,140 -> 521,150 -> 523,150 -> 523,141 -> 523,150 -> 525,150 -> 525,142 -> 525,150
            499,62 -> 499,66 -> 495,66 -> 495,70 -> 510,70 -> 510,66 -> 503,66 -> 503,62
            519,150 -> 519,140 -> 519,150 -> 521,150 -> 521,140 -> 521,150 -> 523,150 -> 523,141 -> 523,150 -> 525,150 -> 525,142 -> 525,150
            472,91 -> 476,91
            488,28 -> 492,28
            507,125 -> 507,127 -> 505,127 -> 505,134 -> 514,134 -> 514,127 -> 513,127 -> 513,125
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            477,119 -> 482,119
            524,159 -> 528,159
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            497,34 -> 501,34
            481,82 -> 485,82
            498,121 -> 498,122 -> 509,122 -> 509,121
            490,115 -> 495,115
            487,82 -> 491,82
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            518,159 -> 522,159
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            499,62 -> 499,66 -> 495,66 -> 495,70 -> 510,70 -> 510,66 -> 503,66 -> 503,62
            503,34 -> 507,34
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            493,88 -> 497,88
            499,62 -> 499,66 -> 495,66 -> 495,70 -> 510,70 -> 510,66 -> 503,66 -> 503,62
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            514,162 -> 514,165 -> 513,165 -> 513,171 -> 526,171 -> 526,165 -> 519,165 -> 519,162
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            507,50 -> 507,53 -> 501,53 -> 501,59 -> 516,59 -> 516,53 -> 511,53 -> 511,50
            527,156 -> 531,156
            498,119 -> 503,119
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            500,32 -> 504,32
            487,88 -> 491,88
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            491,119 -> 496,119
            479,34 -> 483,34
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            519,150 -> 519,140 -> 519,150 -> 521,150 -> 521,140 -> 521,150 -> 523,150 -> 523,141 -> 523,150 -> 525,150 -> 525,142 -> 525,150
            499,62 -> 499,66 -> 495,66 -> 495,70 -> 510,70 -> 510,66 -> 503,66 -> 503,62
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            499,62 -> 499,66 -> 495,66 -> 495,70 -> 510,70 -> 510,66 -> 503,66 -> 503,62
            487,117 -> 492,117
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            496,91 -> 500,91
            519,150 -> 519,140 -> 519,150 -> 521,150 -> 521,140 -> 521,150 -> 523,150 -> 523,141 -> 523,150 -> 525,150 -> 525,142 -> 525,150
            487,75 -> 487,76 -> 499,76 -> 499,75
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            484,91 -> 488,91
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            490,91 -> 494,91
            490,85 -> 494,85
            485,30 -> 489,30
            514,162 -> 514,165 -> 513,165 -> 513,171 -> 526,171 -> 526,165 -> 519,165 -> 519,162
            483,115 -> 488,115
            507,125 -> 507,127 -> 505,127 -> 505,134 -> 514,134 -> 514,127 -> 513,127 -> 513,125
            507,125 -> 507,127 -> 505,127 -> 505,134 -> 514,134 -> 514,127 -> 513,127 -> 513,125
            484,119 -> 489,119
            524,153 -> 528,153
            519,150 -> 519,140 -> 519,150 -> 521,150 -> 521,140 -> 521,150 -> 523,150 -> 523,141 -> 523,150 -> 525,150 -> 525,142 -> 525,150
            491,30 -> 495,30
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            521,156 -> 525,156
            499,62 -> 499,66 -> 495,66 -> 495,70 -> 510,70 -> 510,66 -> 503,66 -> 503,62
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            486,113 -> 491,113
            497,30 -> 501,30
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            507,125 -> 507,127 -> 505,127 -> 505,134 -> 514,134 -> 514,127 -> 513,127 -> 513,125
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            519,150 -> 519,140 -> 519,150 -> 521,150 -> 521,140 -> 521,150 -> 523,150 -> 523,141 -> 523,150 -> 525,150 -> 525,142 -> 525,150
            514,162 -> 514,165 -> 513,165 -> 513,171 -> 526,171 -> 526,165 -> 519,165 -> 519,162
            519,150 -> 519,140 -> 519,150 -> 521,150 -> 521,140 -> 521,150 -> 523,150 -> 523,141 -> 523,150 -> 525,150 -> 525,142 -> 525,150
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            507,125 -> 507,127 -> 505,127 -> 505,134 -> 514,134 -> 514,127 -> 513,127 -> 513,125
            494,117 -> 499,117
            478,91 -> 482,91
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            472,109 -> 472,110 -> 488,110 -> 488,109
            499,62 -> 499,66 -> 495,66 -> 495,70 -> 510,70 -> 510,66 -> 503,66 -> 503,62
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            487,75 -> 487,76 -> 499,76 -> 499,75
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            507,50 -> 507,53 -> 501,53 -> 501,59 -> 516,59 -> 516,53 -> 511,53 -> 511,50
            468,47 -> 468,37 -> 468,47 -> 470,47 -> 470,37 -> 470,47 -> 472,47 -> 472,41 -> 472,47 -> 474,47 -> 474,39 -> 474,47 -> 476,47 -> 476,44 -> 476,47 -> 478,47 -> 478,45 -> 478,47 -> 480,47 -> 480,37 -> 480,47 -> 482,47 -> 482,40 -> 482,47 -> 484,47 -> 484,42 -> 484,47 -> 486,47 -> 486,38 -> 486,47
            480,117 -> 485,117
            494,23 -> 494,22 -> 494,23 -> 496,23 -> 496,17 -> 496,23 -> 498,23 -> 498,20 -> 498,23 -> 500,23 -> 500,14 -> 500,23 -> 502,23 -> 502,13 -> 502,23 -> 504,23 -> 504,15 -> 504,23
            498,121 -> 498,122 -> 509,122 -> 509,121
            487,75 -> 487,76 -> 499,76 -> 499,75
            507,50 -> 507,53 -> 501,53 -> 501,59 -> 516,59 -> 516,53 -> 511,53 -> 511,50
            462,104 -> 462,99 -> 462,104 -> 464,104 -> 464,98 -> 464,104 -> 466,104 -> 466,102 -> 466,104 -> 468,104 -> 468,97 -> 468,104 -> 470,104 -> 470,96 -> 470,104 -> 472,104 -> 472,99 -> 472,104 -> 474,104 -> 474,100 -> 474,104 -> 476,104 -> 476,96 -> 476,104 -> 478,104 -> 478,97 -> 478,104
            """;
}