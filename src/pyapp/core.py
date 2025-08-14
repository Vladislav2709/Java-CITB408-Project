from __future__ import annotations


def generate_greeting(name: str) -> str:
    """Return a friendly greeting for the provided name.

    Empty or whitespace-only names fall back to "World".
    """
    if not name:
        return "Hello, World!"
    normalized = name.strip()
    return f"Hello, {normalized or 'World'}!"