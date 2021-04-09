# My Personal Project: DigiBOP
## Application Proposal

Computer programming is not always easy. The language and syntax are confusing, and the
bugs that come up are frustrating. For beginners, these inconveniences stated above may leave
them discouraged, making programming seem like an unpleasant experience. Some may also have
troubles with spatial and mathematical concepts. I certainly have experienced such things.
I decided to make this application to create a parallel between programming and music,
because the "universal language" will make learning computer programming concepts such as loops much
easier to understand. 


The **Digital Basic Oscillator Program (DigiBOP)** allows us to program/code our own music via jigsaw-puzzle code.
These jigsaws-puzzle codes, also known as "blocks", can form functions called "Scripts" that play when they are called.
These scripts are then played together in a "Channel" by a "Player". 
The prebuilt code blocks include loops, note, and rests. The final product will include a virtual keyboard
that makes assigning pitches much easier.


My program makes it extremely easy to create electronic music with no prior music knowledge.
For others, this program will be their first experience with coding.
Using this program will be extremely fun, and many people may discover their talent for music
and interest in computer science.

## User Stories

**My user stories**:
- As a user, I want to be able to play a certain note in a loop at a specific beat per minute.
- As a user, I want to be able to add multiple loops/notes/rests blocks into a script.
- As a user, I want to be able to add multiple scripts into a channel and play them simultaneously at a specific
rhythm.
- As a user, I want to be able to edit,delete, and view scripts that have already been made.
- As a user, I want to be able to control the pitch/duration/instrument of pre-made blocks.
- As a user, I want to be able to save as many channels as different files.
- As a user, I want to choose and load channels from my local device.
- As a user, I want to be asked to save my file when I try to quit the program.
- As a user, I want to be given an option to load, save, and "save as" channels from the main menu.
- As a user, I want to be able to save my channel to the same destination after I have already saved/loaded it.
- As a user, I want to be able to move/select/delete multiple scripts in a workspace UI.

## Phase 4: Task 2

- There is a type hierarchy for the interface Block, which has NoteBlock and RestBlock. NoteBlock is the
    supertype of LoopBlock and ChordBlock.

## Phase 4: Task 3

- If I had more time to work on my project, I would definitely work on lowering the coupling for my ui package.
This can be done by using an Observer design pattern between the SynthEditor and the other classes. 
- I will also attempt to lower the number of bidirectional associations between the ui classes. 
- Moreover, I would aim to remove semantic coupling in my code, especially in the BlockEditorUI. 
- I would improve the cohesion in some of my ui classes, such as SynthEditor which should contain classes
for selectedScript and selectedTool, and one with a Save/Load/Manager.
- BlockEditorUI would be better if there was a panel class for each Block type and placed as a CardLayout
in ScriptEditorUI (Single-responsibility principle).
