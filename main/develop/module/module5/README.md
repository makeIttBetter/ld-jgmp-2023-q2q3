1.1 Git hooks
1.1.1 Specify template of commit message:
Creating the commit-msg hook:
In your local repository, navigate to .git/hooks/ directory and create a file named commit-msg.

Inside commit-msg, add a script:
Here's a breakdown of the script with explanations:

bash
Copy code
#!/bin/sh

# Pattern we want the commit message to match
pattern="^TICKET-[0-9]+: .+"

# 'grep' searches for a pattern in the input. 
# '-q' means quiet mode - don't output anything.
# '-E' is extended regex mode, which allows the use of extended regular expressions.
# '$1' is a shell variable representing the first argument passed to the script. 
# In the context of the commit-msg hook, $1 is the path to the temporary file that contains the commit message.
if ! echo "$1" | grep -qE "$pattern"; then
    # Print an error message if the pattern doesn't match
    echo "error: invalid commit message format"
    echo "Commit message should match pattern: TICKET-XXX: Description"
    exit 1 # Exit with an error status
fi

Make the hook executable:
To ensure the hook can be run, change its permissions with the chmod command:
bash
Copy code
chmod +x .git/hooks/commit-msg
1.1.2 Check build before committing:
Creating the pre-commit hook:
Navigate to .git/hooks/ and create (or edit if it exists) a file named pre-commit.

Inside pre-commit, add a script:

bash
Copy code
#!/bin/sh

# Call your build script. If your build script exits with an error, the commit will be aborted.
# This is just a placeholder; replace './build.sh' with the command to run your build.
if ! ./build.sh; then
    echo "Build failed, aborting commit"
    exit 1
fi

Make the hook executable:
bash
Copy code
chmod +x .git/hooks/pre-commit
1.1.3 Check for uncommitted changes before pushing:
Creating the pre-push hook:
Navigate to .git/hooks/ and create (or edit) a file named pre-push.

Inside pre-push, add a script:

bash
Copy code
#!/bin/sh

# 'git status --porcelain' provides a machine-readable output of git status.
# If there are changes, this command outputs them. If there are no changes, it outputs nothing.
if [ -n "$(git status --porcelain)" ]; then
    echo "You have uncommitted changes. Please commit or stash them before pushing."
    exit 1
fi

Make the hook executable:
bash
Copy code
chmod +x .git/hooks/pre-push
1.2 Copy a folder with history:
Clone the source repository:
bash
Copy code
git clone <source-repo-url> source-repo
cd source-repo
Filter out the specific folder's history:
The filter-branch command rewrites Git revision history. The --subdirectory-filter option tells it to rewrite the repository to only contain history related to the given folder.
bash
Copy code
git filter-branch --subdirectory-filter <your-folder-name> -- --all
Push to a new repository:
Create a new empty repository on GitLab, then set its URL as the 'origin' remote and push the filtered content:
bash
Copy code
git remote set-url origin <new-repo-url>
git push -u origin master
2.1 & 2.2