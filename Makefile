JAVA  := /home/cam/Downloads/jdk1.8.0_111/bin/java
JAVAC := /home/cam/Downloads/jdk1.8.0_111/bin/javac

SRC := chess/ChessController.java chess/Main.java chess/NewGameWindowController.java chess/AboutWindowController.java
OBJ := $(patsubst %.java,%.class,$(SRC))
MAIN := chess.Main

all: $(OBJ)

$(OBJ): $(SRC)
	$(JAVAC) $^

run:
	$(JAVA) $(MAIN)

clean:
	rm -f $(OBJ)

.PHONY: all clean run
