package client.commands;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Command {
    MOVE("/move"),
    ANGLE("/angle"),
    PUT_UP("/pu"),
    COLOR("/color"),
    LIST_OF_FIGURES("/figures"),
    LIST_OF_STEPS("/steps"),
    PUT_DOWN("/pd");


    public final String relUrl;

}
