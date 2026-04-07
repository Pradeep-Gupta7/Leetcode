class Robot {

    int x, y, dir;
    int width, height;
    int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    int cycle;

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
        this.dir = 0; // East

        this.cycle = 2 * (width + height) - 4;
    }

    public void step(int num) {
        if (cycle == 0) {
            int total = 2 * (width + height) - 4; // will be 0 or negative
            if (width == 1) {
                // move up/down
                int move = num % (2 * (height - 1));
                while (move-- > 0) {
                    if (dir == 1 && y + 1 < height) y++;
                    else if (dir == 3 && y - 1 >= 0) y--;
                    else dir = (dir + 2) % 4; // reverse direction
                }
            } else {
                // move left/right
                int move = num % (2 * (width - 1));
                while (move-- > 0) {
                    if (dir == 0 && x + 1 < width) x++;
                    else if (dir == 2 && x - 1 >= 0) x--;
                    else dir = (dir + 2) % 4; // reverse direction
                }
            }
            return;
        }

        num %= cycle;

        // 🔥 FULL LOOP CASE (important)
        if (num == 0 && (x != 0 || y != 0)) {
            num = cycle;
        } else if (num == 0) {
            dir = 3; // South
            return;
        }

        while (num-- > 0) {
            int nx = x + dirs[dir][0];
            int ny = y + dirs[dir][1];

            // boundary check
            if (nx < 0 || nx >= width || ny < 0 || ny >= height) {
                dir = (dir + 1) % 4; // counterclockwise
                nx = x + dirs[dir][0];
                ny = y + dirs[dir][1];
            }

            x = nx;
            y = ny;
        }
    }

    public int[] getPos() {
        return new int[]{x, y};
    }

    public String getDir() {
        String[] d = {"East", "North", "West", "South"};
        return d[dir];
    }
}