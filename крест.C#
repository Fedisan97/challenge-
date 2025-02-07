using System;

class TicTacToe
{
    static void Main()
    {
        char[,] board = new char[3, 3];
        InitializeBoard(board);
        
        bool gameOver = false;
        int turn = 0; // 0 for X, 1 for O

        while (!gameOver)
        {
            PrintBoard(board);
            MakeMove(board, turn);
            if (CheckWin(board))
            {
                Console.WriteLine("Player {0} wins!", turn == 0 ? 'X' : 'O');
                gameOver = true;
            }
            else if (IsDraw(board))
            {
                Console.WriteLine("It's a draw!");
                gameOver = true;
            }
            turn = (turn + 1) % 2;
        }

        PrintBoard(board); // Final board state after the game is over
    }

    static void InitializeBoard(char[,] board)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i, j] = '-';
            }
        }
    }

    static void PrintBoard(char[,] board)
    {
        Console.Clear();
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                Console.Write(board[i, j]);
                if (j != 2)
                    Console.Write("|");
            }
            Console.WriteLine();
            if (i != 2)
                Console.WriteLine("-----");
        }
    }

    static void MakeMove(char[,] board, int player)
    {
        bool validMove = false;
        do
        {
            Console.Write("\nPlayer {0}, enter your move (row column): ", player == 0 ? 'X' : 'O');
            string input = Console.ReadLine();
            int row = input[0] - '0'; // Convert first character to integer
            int col = input[2] - '0'; // Convert third character to integer

            if (row >= 1 && row <= 3 && col >= 1 && col <= 3 && board[row - 1, col - 1] == '-')
            {
                board[row - 1, col - 1] = player == 0 ? 'X' : 'O';
                validMove = true;
            }
            else
            {
                Console.WriteLine("Invalid move. Try again.");
            }
        } while (!validMove);
    }

    static bool CheckWin(char[,] board)
    {
        // Check rows
        for (int i = 0; i < 3; i++)
        {
            if (board[i, 0] != '-' && board[i, 0] == board[i, 1] && board[i, 1] == board[i, 2])
                return true;
        }

        // Check columns
        for (int j = 0; j < 3; j++)
        {
            if (board[0, j] != '-' && board[0, j] == board[1, j] && board[1, j] == board[2, j])
                return true;
        }

        // Check diagonals
        if (board[0, 0] != '-' && board[0, 0] == board[1, 1] && board[1, 1] == board[2, 2])
            return true;
        if (board[0, 2] != '-' && board[0, 2] == board[1, 1] && board[1, 1] == board[2, 0])
            return true;

        return false;
    }

    static bool IsDraw(char[,] board)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i, j] == '-')
                    return false;
            }
        }
        return true;
    }
}
