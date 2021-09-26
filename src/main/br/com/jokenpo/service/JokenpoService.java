package main.br.com.jokenpo.service;

import main.br.com.jokenpo.enumeration.JokenpoEnum;
import main.br.com.jokenpo.enumeration.ResultEnum;

import java.util.Random;

public class JokenpoService {

    public int[] play(JokenpoEnum entryMove) {
        Random random = new Random();
        int[] returnArray = new int[2];
        int randomMove = random.nextInt(3);

        returnArray[0] = randomMove;
        JokenpoEnum jokenpoEnum = JokenpoEnum.toEnum(randomMove);

        int jokenpoResult = this.checkMoveWinner(entryMove, jokenpoEnum);
        returnArray[1] = jokenpoResult;

        return returnArray;
    }

    private Integer checkMoveWinner(JokenpoEnum entryMove, JokenpoEnum randomMove) {
        if (entryMove.equals(randomMove)) {
            return ResultEnum.DRAW.getResult();
        } if (entryMove.equals(JokenpoEnum.PAPER)) {
            switch (randomMove) {
                case ROCK:
                    return ResultEnum.WIN.getResult();
                case SCISSOR:
                    return ResultEnum.LOST.getResult();
            }
        } else if (entryMove.equals(JokenpoEnum.ROCK)) {
            switch (randomMove) {
                case SCISSOR:
                    return ResultEnum.WIN.getResult();
                case PAPER:
                    return ResultEnum.LOST.getResult();
            }
        } else {
            switch (randomMove) {
                case PAPER:
                    return ResultEnum.WIN.getResult();
                case ROCK:
                    return ResultEnum.LOST.getResult();
            }
        }
        return ResultEnum.DRAW.getResult();
    }
}
