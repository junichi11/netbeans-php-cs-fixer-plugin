# NetBeans PHP CS Fixer Plugin

This plugin provides support for the PHP CS Fixer.
You can run fix command with this plugin.

## PHP CS Fixer

https://github.com/FriendsOfPHP/PHP-CS-Fixer

## Available versions of PHP CS Fixer

- 1.x
- 2.x (v0.6.0)

## Requirements

- NetBeans 8.2+

## Available Commands
- fix
- fix --dry-run
- self-update

## Features

- Run commands
- Run on Save

## Settings

### Global

 `Tools > Options > PHP > Framework & Tools > PHP CS Fixer`
- set PHP CS Fixer path
- select a version
- set fix command options

### Project

`project properties > PHP CS Fixer`
- If you would like to use settings each projects, please, check `use project settings`

### Customizing Options

If you would like to use some options, please check the custom checkbox, then please add them to it.

e.g. `--cache-file=/path/to/.php_cs.cache`

### Show an output window

If you would like to avoid showing the output window, please uncheck it. (Checked by default)

### Run self-update command on boot

If you would like to run the self-update command on boot, please check it. (Unchecked by default)

## Usage

1. Right-click
    - Project
    - Directory
    - File
    - Editor
2. PHP CS Fixer
    - fix
    - fix --dry-run
    - self-update

## Download

- https://plugins.netbeans.apache.org/catalogue/?id=36
- https://github.com/junichi11/netbeans-php-cs-fixer-plugin/releases

## NOTE

- Run on save feature may not work sometimes. Now after 1 second, the command is run. But I don't have another idea at the moment, sorry.

## Donation

- https://github.com/sponsors/junichi11

## License

The Apache License, Version 2.0

```
Copyright 2022 junichi11.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
