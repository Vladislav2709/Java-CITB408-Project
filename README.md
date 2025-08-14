# pyapp

A starter Python project with a simple CLI and tests.

## Installation

Using `pipx` (recommended):

```bash
pipx install .
```

Editable dev install:

```bash
python -m venv .venv
. .venv/bin/activate
pip install -e .[dev]
```

## Usage

```bash
pyapp --name Alice
pyapp -n Bob --shout
```

## Testing

```bash
pytest
```