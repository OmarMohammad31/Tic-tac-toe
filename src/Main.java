import java.util.*;
public class Main
{
    static Scanner input = new Scanner (System.in);
    public static boolean isWinner(char[][] game)
    {
        return ((game[0][0]==game[0][1] && game[0][1]==game[0][2] && game[0][1]!='-') //1st row
                || (game[1][0]==game[1][1] && game[1][1]==game[1][2] && game[1][1]!='-')//2nd row
                || (game[2][0]==game[2][1] && game[2][1]==game[2][2] && game[2][1]!='-') //3rd row
                || (game[0][0]==game[1][0]&&game[1][0]==game[2][0]  && game[1][0]!='-') //1st column
                || (game[0][1]==game[1][1]&&game[1][1]==game[2][1]  && game[1][1]!='-') //2nd column
                || (game[0][2]==game[1][2]&&game[1][2]==game[2][2]  && game[1][2]!='-') //3rd column
                || (game[0][0]==game[1][1]&&game[1][1]==game[2][2]  && game[1][1]!='-') //principal diagonal
                || (game[0][2]==game[1][1]&&game[1][1]==game[2][0]  && game[1][1]!='-')); //off diagonal
    }
    public static boolean isEnded(char[][] game)
    {
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                if (game[i][j] == '-')
                    return false;
            }
        }
        return true;
    }
    public static char[] validateInput(char[][] game, char row, char column)
    {
        while (!(row >= '1' && row <= '3' && column >= '1' && column <= '3'))
        {
            System.out.println("Enter a valid position: ");
            row = input.next().charAt(0);
            column = input.next().charAt(0);
        }
        while (game[row-'0' -1][column-'0' -1]!='-')
        {
            System.out.println("This posision has been taken, Enter another one:");
            row = input.next().charAt(0);
            column = input.next().charAt(0);
        }
        char[] move = new char[2];
        move[0] = row;
        move[1] = column;
        return move;
    }
    public static void showGame(char[][] game)
    {
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                System.out.print(game[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void startGame(char[][] game)
    {
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                game[i][j] = '-';
            }
        }
    }
    public static void main(String[] args)
    {
        char[][] game = new char[3][3];
        char cont = 'y';
        while (cont == 'y')
        {
            System.out.println("New game");
            System.out.println("Enter first player character:");
            char player1 = input.next().charAt(0);
            System.out.println("Enter second player characher:");
            char player2 = input.next().charAt(0);
            startGame(game);
            showGame(game);
            boolean turn=true;
            while (true)
            {
                System.out.print("choose player");
                System.out.print(turn?1:2);
                System.out.println(" move(position)");
                char row = input.next().charAt(0);
                char column = input.next().charAt(0);
                char[] move = new char[2];
                move = validateInput(game, row, column);
                game[move[0]-'0' -1][move[1]-'0' -1] = (turn?player1:player2);
                showGame(game);
                if(isWinner(game))
                {
                    System.out.print("player");
                    System.out.print(turn?1:2);
                    System.out.println(" has won!");
                    System.out.println("Do you want to play another game?y/n");
                    cont = input.next().charAt(0);
                    break;
                }
                else if (isEnded(game))
                {
                    System.out.print("Draw!");
                    System.out.println("DO you want to play another game?y/n");
                    cont = input.next().charAt(0);
                    break;
                }
                turn = !turn;
            }
        }
    }
}