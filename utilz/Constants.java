package utilz;

import main.Game;

public class Constants {
    public static class UI{
        public static class Buttons{
            public static final int B_WIDTH_DEFAULT=140;
            public static final int B_HEIGHT_DEFAULT=56;
            public static final int B_HEIGHT= (int) (B_HEIGHT_DEFAULT* Game.SCALE);
            public static final int B_WIDTH= (int) (B_WIDTH_DEFAULT* Game.SCALE);

        }
    }
    public static class Directions{
        public static final int LEFT=0;
        public static final int UP=1;
        public static final int RIGHT=2;
        public static final int DOWN=3;
    }
    public static class PlayerConstants{
        public static String PATH_1="/Captain Clown Nose/Captain Clown Nose without Sword/";
        public static String PATH_2="/Captain Clown Nose/Captain Clown Nose with Sword/";

        public static final int RUNNING = 0;
        public static final int IDLE = 1;
        public static final int JUMPING = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;
        public static final int MAX_PLAYER_CONSTANTS=8;

        public static int GetSpriteAmount(int player_action){
            return switch (player_action) {
                case RUNNING -> 6;
                case IDLE -> 5;
                case JUMPING, ATTACK_1, ATTACK_JUMP_1, ATTACK_JUMP_2 -> 3;
                case FALLING -> 1;
                case GROUND -> 2;
                case HIT -> 4;
                default -> 7;
            };

        }
        public static String GetPathName(int player_action){
            return switch (player_action) {
                case RUNNING -> PATH_1 + "02-Run/Run 0";
                case IDLE -> PATH_1 + "01-Idle/Idle 0";
                case JUMPING -> PATH_1 + "03-Jump/Jump 0";
                case FALLING -> PATH_1 + "04-Fall/Fall 0";
                case GROUND -> PATH_1 + "05-Ground/Ground 0";
                case HIT -> PATH_1 + "06-Hit/Hit 0";
                case ATTACK_1 -> PATH_2 + "15-Attack 1/Attack 1 0";
                case ATTACK_JUMP_1 -> PATH_2 + "18-Air Attack 1/Air Attack 1 0";
                case ATTACK_JUMP_2 -> PATH_2 + "19-Air Attack 2/Air Attack 2 0";
                default -> PATH_2 + "ERROR_PIC/ERR_PIC 0";
            };
        }
    }
}
