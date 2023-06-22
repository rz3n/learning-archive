#!/bin/bash

nerdctl run --rm --name python -v './:/lab'  -it python:local fish
