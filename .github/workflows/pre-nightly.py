"""Replace version in pom.xml for server backups."""

import os
import xml.etree.ElementTree as Et

from github import Github, Branch

REPO_ID: int = 385764384
BRANCH: str = "main"

g: Branch = Github(os.environ["GTOKEN"]).get_repo(REPO_ID).get_branch(BRANCH)


def get_root(file_path: str) -> Et.Element:
    """Retrieve xml root element from pom."""
    return Et.parse(file_path).getroot()


def save_pom(file_path: str, tree_root: Et.Element) -> None:
    """Cleanup xml and save it."""
    content: str = Et.tostring(tree_root).decode()
    clean: str = content.replace("ns0:", '').replace(":ns0", '')

    with open(file_path, 'w') as f:
        f.write(clean)


def get_commit_id() -> str:
    """Retrieve the repository id."""
    return g.commit.sha[:7]


def main() -> None:
    """Entry point for the program."""
    file: str = '../pom.xml'
    file_dest = ".github/nightly-pom.xml"

    xml_root = get_root(file)
    xml_root[3].text += f"-nightly_{get_commit_id()}"

    xml_root[8][0][1].text = xml_root[8][0][1].text.replace("stable-builds", "nightly-builds")

    save_pom(file_dest, xml_root)


if __name__ == '__main__':
    main()