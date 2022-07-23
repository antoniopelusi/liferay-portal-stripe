/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

Path buildGradlePath = projectPath.resolve("build.gradle")

Files.deleteIfExists buildGradlePath

def buildDir = projectPath.toFile()

def webINFDir = new File(buildDir, "src/main/webapp/WEB-INF")

String liferayVersion = request.properties.get("liferayVersion")

char minorVersion = liferayVersion.charAt(2)

File liferayLayoutTemplatesXML = new File(webINFDir, "liferay-layout-templates.xml");

def newLiferayLayoutTemplatesContent = liferayLayoutTemplatesXML.text.replace("7.0", "7." + minorVersion).replace("7_0", "7_" + minorVersion)

liferayLayoutTemplatesXML.text = newLiferayLayoutTemplatesContent