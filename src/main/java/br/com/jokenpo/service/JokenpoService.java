package br.com.jokenpo.service;

import br.com.jokenpo.enumeration.JokenpoEnum;
import br.com.jokenpo.enumeration.ResultEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokenpoService {

    private List<JokenpoEnum> entries = new ArrayList<>();

    public int[] play() {

        int[] returnArray = new int[4];

        returnArray[0] = this.checkMoveWinner(entries.get(0), entries.get(1));
        returnArray[1] = this.checkMoveWinner(entries.get(1), entries.get(0));

        returnArray[2] = getEntries().get(0).getMoveCode();
        returnArray[3] = getEntries().get(1).getMoveCode();

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

    public void setEntry(JokenpoEnum entry) {
        if (getEntriesSize() > 2) {
            entries.clear();
        }
        this.entries.add(entry);
    }

    public List<JokenpoEnum> getEntries() {
        return this.entries;
    }

    public Integer getEntriesSize() {
        return this.entries.size();
    }
}
