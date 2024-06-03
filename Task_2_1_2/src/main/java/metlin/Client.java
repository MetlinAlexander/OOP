package metlin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;
import java.net.UnknownHostException;

import metlin.utils.Primes;

/**
 * client class.
 */
public class Client {

    /**
     * private contructor for client.
     */
    private Client() { }

    /**
     * solver method for client.
     *
     * @param port port for communication
     */
    public static void solver(int port) {
        try (Socket socket = new Socket("localhost", port);
             Scanner in = new Scanner(socket.getInputStream());
             PrintWriter out = new PrintWriter(socket.getOutputStream(),
                     true)) {
            while (in.hasNextLine()) {
                String input = in.nextLine();
                if (!input.isEmpty()) {
                    System.out.println(input);
                }
                if (in.hasNextInt()) {
                    int isPrime = Primes.isPrime(in.nextInt()) ? 1 : 0;
                    out.println(isPrime);
                }
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
