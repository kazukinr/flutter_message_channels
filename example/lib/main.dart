import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_message_channels/flutter_message_channels.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  String _platformVersionJson = 'Unknown';
  String _platformVersionProto = 'Unknown';
  String _platformVersionPigeon = 'Unknown';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    String platformVersionJson;
    String platformVersionProto;
    String platformVersionPigeon;

    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    try {
      platformVersion = await FlutterMessageChannels.platformVersion ??
          'Unknown platform version';
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    try {
      final PlatformInfoJson? infoJson =
          await FlutterMessageChannels.platformInfoJson;
      platformVersionJson = infoJson?.version ?? 'Unknown platform version';
    } on PlatformException {
      platformVersionJson = 'Failed to get platform version via JSON.';
    }

    try {
      final PlatformInfoProto? infoProto =
          await FlutterMessageChannels.platformInfoProto;
      platformVersionProto = infoProto?.version ?? 'Unknown platform version';
    } on PlatformException {
      platformVersionProto = 'Failed to get platform version via proto.';
    }

    try {
      final PlatformInfoPigeon infoPigeon =
          await FlutterMessageChannels.platformInfoPigeon;
      platformVersionPigeon = infoPigeon.version ?? 'Unknown platform version';
    } on PlatformException {
      platformVersionPigeon = 'Failed to get platform version via pigeon.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
      _platformVersionJson = platformVersionJson;
      _platformVersionProto = platformVersionProto;
      _platformVersionPigeon = platformVersionPigeon;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
              child: Text('Running on: $_platformVersion'),
            ),
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
              child: Text('Running on: $_platformVersionJson'),
            ),
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
              child: Text('Running on: $_platformVersionProto'),
            ),
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 8, horizontal: 16),
              child: Text('Running on: $_platformVersionPigeon'),
            ),
          ],
        ),
      ),
    );
  }
}
