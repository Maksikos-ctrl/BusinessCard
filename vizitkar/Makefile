# Makefile for Vizitkar project

# Define variables
MAIN_CLASS = com.vizitkar.Main
COMPILE_CMD = mvn compile
RUN_CMD = mvn exec:java -Dexec.mainClass=$(MAIN_CLASS)

# Default target
.DEFAULT_GOAL := run

# Compile the project
compile:
	@echo "Compiling the project..."
	$(COMPILE_CMD)

# Run the project
run: compile
	@echo "Running the project..."
	$(RUN_CMD)

# Clean the project
clean:
	@echo "Cleaning the project..."
	mvn clean

# Help
help:
	@echo "Usage: make [target]"
	@echo ""
	@echo "Targets:"
	@echo "  compile   : Compile the project"
	@echo "  run       : Run the project"
	@echo "  clean     : Clean the project"
	@echo "  help      : Show this help message"

# Ensure targets are not treated as file names
.PHONY: compile run clean help
