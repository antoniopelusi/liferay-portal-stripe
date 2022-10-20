import * as vscode from 'vscode';

import { CompletionItemProviderImpl } from './lib/languageFeatureProviders/CompletionItemProviderImpl';
import { DefinitionProviderImpl } from './lib/languageFeatureProviders/DefinitionProviderImpl';
import { DocumentFormattingEditProviderImpl } from './lib/languageFeatureProviders/DocumentFormattingEditProviderImpl';
import { ReferenceProviderImpl } from './lib/languageFeatureProviders/ReferenceProviderImpl';

export function activate(context: vscode.ExtensionContext) {
	console.log('Registering language feature providers...');

	context.subscriptions.push(
		vscode.languages.registerCompletionItemProvider(
			{ pattern: '**/*.{function,macro,testcase}' },
			new CompletionItemProviderImpl(context),
			'.',
			'#',
			' ',
		),
	);
	context.subscriptions.push(
		vscode.languages.registerDefinitionProvider(
			{
				pattern: '**/*.{function,macro,testcase}',
			},
			new DefinitionProviderImpl(),
		),
	);
	context.subscriptions.push(
		vscode.languages.registerReferenceProvider(
			{ pattern: '**/*.{function,macro,path,testcase}' },
			new ReferenceProviderImpl(),
		),
	);
	context.subscriptions.push(
		vscode.languages.registerDocumentFormattingEditProvider(
			{
				pattern: '**/*.{function,macro,testcase}',
			},
			new DocumentFormattingEditProviderImpl(),
		),
	);

	console.log(
		'Congratulations, your extension "poshi-language-support" is now active!',
	);
}

export function deactivate() {}
