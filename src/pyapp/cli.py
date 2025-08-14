from __future__ import annotations

import argparse
from typing import List, Optional

from .core import generate_greeting


def parse_args(argv: Optional[List[str]] = None) -> argparse.Namespace:
    parser = argparse.ArgumentParser(prog="pyapp", description="Simple greeting CLI.")
    parser.add_argument("--name", "-n", default="World", help="Name to greet")
    parser.add_argument("--shout", action="store_true", help="Uppercase the greeting")
    return parser.parse_args(argv)


def main(argv: Optional[List[str]] = None) -> int:
    args = parse_args(argv)
    message = generate_greeting(args.name)
    if args.shout:
        message = message.upper()
    print(message)
    return 0


if __name__ == "__main__":
    raise SystemExit(main())