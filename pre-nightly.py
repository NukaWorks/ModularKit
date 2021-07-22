import xml.etree.ElementTree as element_tree
import sys
from github import Github

githubToken = ""
gRepo = ""


def get_root(file_path: str):
    return element_tree.parse(file_path).getroot()


def save_pom(file_path: str, tree_root) -> None:
    with open(file_path, "w") as f:
        f.write(element_tree.tostring(tree_root).decode().replace("ns0:", '').replace(":ns0", ''))


def get_commit_id():
    return gRepo.commit.sha[:7]


def main() -> None:
    for arg in sys.argv[1:]:
        print(f"Shitty args : {arg}")
        githubToken = arg
    gRepo = Github(githubToken).get_repo(385764384).get_branch("main")
    file: str = 'pom.xml'
    fileDest = ".github/nightly-pom.xml"
    xml_root = get_root(file)
    xml_root[3].text = f"v1.4.3-nightly_{get_commit_id()}"
    xml_root[8][0][1].text = xml_root[8][0][1].text.replace("stable-builds", "nightly-builds")
    save_pom(fileDest, xml_root)


if __name__ == '__main__':
    main()
