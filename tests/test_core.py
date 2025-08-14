from pyapp.core import generate_greeting


def test_generate_greeting_with_name() -> None:
    assert generate_greeting("Alice") == "Hello, Alice!"


def test_generate_greeting_trims_and_default() -> None:
    assert generate_greeting("  Bob  ") == "Hello, Bob!"
    assert generate_greeting("") == "Hello, World!"