import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Image Display Example',
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Display Image Example'),
        ),
        body: Center(
          child: Image.network(
            'https://flutter.github.io/assets-for-api-docs/assets/widgets/owl.jpg',
            width: 300,
            height: 300,
          ),
        ),
      ),
    );
  }
}
